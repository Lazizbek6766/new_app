package uz.megasoft.newapp.data.repositoryImpl

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import uz.megasoft.newapp.domain.model.Table
import uz.megasoft.newapp.domain.repository.TableRepository

class TableTableRepositoryImpl(
    private val firestore: FirebaseFirestore
):TableRepository {

    private val tablesCollection = firestore.collection("tables")

    override suspend fun addTable(table: Table): Boolean {
        return try {
            tablesCollection.document(table.id).set(table).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getTables(): List<Table> {
        return try {
            val snapshot = tablesCollection.get().await()
            snapshot.toObjects(Table::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}