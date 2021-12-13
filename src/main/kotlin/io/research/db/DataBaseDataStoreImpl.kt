package io.research.db

import io.research.objects.Customer

//Class that will be used when a Database Version of the DataStore is created.
class DataBaseDataStoreImpl : DataStore {
    override fun getAllCustomers(): List<Customer> {
        TODO("Not yet implemented")
    }

    override fun addCustomer(customer: Customer): Boolean {
        TODO("Not yet implemented")
    }

    override fun editCustomer(customerId: Int, customer: Customer): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteCustomer(customerId: Int): Boolean {
        TODO("Not yet implemented")
    }
}