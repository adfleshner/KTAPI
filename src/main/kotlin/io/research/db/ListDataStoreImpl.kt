package io.research.db

import io.research.objects.Customer

//Using an in memory Mutable List to Back the data store.
class ListDataStoreImpl() : DataStore {

    private val customers = mutableListOf<Customer>()
    private var count = 0

    override fun getAllCustomers(): List<Customer> {
        return customers
    }

    override fun addCustomer(customer: Customer): Boolean {
        val newCustomer = Customer(count,customer.firstName,customer.lastName)
        count++//auto inc count.
        return customers.add(newCustomer)
    }

    override fun editCustomer(customerId: Int, customer: Customer): Boolean {
        val oldCustomerIndex = customers.indexOfFirst { it.id == customerId }
        if (oldCustomerIndex == -1) {
            return false
        }
        customers[oldCustomerIndex] = customer
        return true
    }

    override fun deleteCustomer(customerId: Int): Boolean {
        return customers.removeIf { it.id == customerId }
    }

}