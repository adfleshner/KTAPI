package io.research

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.cio.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.research.db.DataStore
import io.research.objects.Customer
import io.research.objects.toJson

private const val URL = "127.0.0.1"
private const val PORT = 42069
private val gson = Gson()

object Server {

    fun start(dataStore: DataStore) {
        embeddedServer(Netty, port = PORT, host = URL) {
            install(StatusPages) {
                exception<Throwable> { e ->
                    call.respondText(e.localizedMessage, ContentType.Text.Plain, HttpStatusCode.InternalServerError)
                }
            }
            routing {

                //Generic
                get("/") {
                    call.respond(HttpStatusCode.OK, "OK")
                }

                post("/") {
                    val request = call.receive<Request>()
                    call.respond(request)
                }

                //Users

                //Gets all users.
                get("/user/all") {
                    call.respond(HttpStatusCode.OK, dataStore.getAllCustomers().toJson())
                }

                //The receive<Customer> not working not sure why so I am using receiveText and then using gson to make it an object.
                post("/user/add") {
                    call.receiveText().let { body ->
                        gson.fromJson(body, Customer::class.java).let { customer ->
                            dataStore.addCustomer(customer)
                            call.respond(HttpStatusCode.OK, "Added: ${customer.firstName} ${customer.lastName}!")
                        }
                    }
                }

                //Commented code currently clears the content not 100% sure why.
                post("/user/edit/{id}") {
//                    call.receiveText().let { body ->
//                        gson.fromJson(body, Customer::class.java).let { customer ->
//                            val customerid = call.parameters["id"]
//                            customerid?.toInt()?.let { it1 ->
//                                dataStore.editCustomer(it1, customer)
//                                call.respond(
//                                    HttpStatusCode.Accepted,
//                                    "Edited: ${customer.firstName} ${customer.lastName}!"
//                                )
//                            } ?: run {
//                                call.respond(HttpStatusCode.BadRequest, "Nope")
//                            }
//                        }
//                    }
                }

                //Deletes a user via id.
                delete("/user/delete/{id}") {
                    val idParam = call.parameters["id"]
                    val customerid = idParam!!.toInt()
                    val removedCustomer = dataStore.deleteCustomer(customerid)
                    if (removedCustomer) {
                        call.respond(HttpStatusCode.Accepted, "User Removed!")
                    } else {
                        call.respond(HttpStatusCode.NotFound, "User Not Found at id $customerid")
                    }
                }
            }
        }.start(wait = true)
    }
}

