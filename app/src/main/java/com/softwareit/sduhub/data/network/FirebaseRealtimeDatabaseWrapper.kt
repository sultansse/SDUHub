package com.softwareit.sduhub.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber


abstract class FirebaseRealtimeDatabaseWrapper<T> {
    private val db = FirebaseDatabase.getInstance()

    protected abstract fun getTableName(): String
    protected abstract fun getClassType(): Class<T>

    private val _getDataLiveData = MutableLiveData<T?>()
    val getDataLiveData: LiveData<T?> = _getDataLiveData

    private val _updateLiveData = MutableLiveData<T?>()
    val updateLiveData: LiveData<T?> = _updateLiveData

    init {
        db.getReference(getTableName()).addValueEventListener(updateListener())
    }

    private fun updateListener() = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            _updateLiveData.postValue(snapshot.getValue(getClassType()))
        }

        override fun onCancelled(error: DatabaseError) {
            error.let {
                Timber.e(it.message)
            }
        }
    }

    fun saveData(value: T, successSave: ((Boolean) -> Unit)? = null) {
        db.getReference(getTableName()).setValue(value) { error, _ ->
            successSave?.invoke(error == null)
            error?.let {
                Timber.e(it.message)
            }
        }
    }

    fun getData() {
        db.getReference(getTableName()).get().addOnSuccessListener {
            _getDataLiveData.postValue(it.getValue(getClassType()))
        }
    }

    fun removeData() {
        db.getReference(getTableName()).removeValue()
    }
}
