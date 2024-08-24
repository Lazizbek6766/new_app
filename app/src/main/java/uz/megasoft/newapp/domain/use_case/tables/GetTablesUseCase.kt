package uz.megasoft.newapp.domain.use_case.tables

import uz.megasoft.newapp.domain.model.Table
import uz.megasoft.newapp.domain.repository.TableRepository

class GetTablesUseCase(
    private val repository: TableRepository
) {
    suspend operator fun invoke(): List<Table> {
        return repository.getTables()
    }
}