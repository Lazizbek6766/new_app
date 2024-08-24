package uz.megasoft.newapp.domain.repository

import uz.megasoft.newapp.domain.model.Table

interface TableRepository {

    suspend fun addTable(table: Table): Boolean
    suspend fun getTables(): List<Table>

}