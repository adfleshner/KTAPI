package io.research.db

import io.research.objects.Customer

interface DataStore {

    fun getAllCustomers(): List<Customer>
    fun addCustomer(customer: Customer): Boolean
    fun editCustomer(customerId: Int, customer: Customer): Boolean
    fun deleteCustomer(customerId: Int): Boolean

}
