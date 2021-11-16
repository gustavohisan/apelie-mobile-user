package com.gustavohisan.apelieuser.domain.usecase.address

import com.gustavohisan.apelieuser.domain.model.address.InsertAddressErrorType
import com.gustavohisan.apelieuser.domain.model.address.InsertAddressState
import com.gustavohisan.apelieuser.domain.repository.address.AddressRepository

class InsertAddress(private val addressRepository: AddressRepository) {

    suspend operator fun invoke(
        city: String,
        complement: String,
        district: String,
        number: String,
        state: String,
        street: String,
        zipCode: String
    ): InsertAddressState {
        val errors = checkFields(city, district, number, state, street, zipCode)
        return if (errors.isNotEmpty()) {
            InsertAddressState.Error(errors)
        } else {
            insertAddress(city, complement, district, number, state, street, zipCode)
        }
    }

    private suspend fun insertAddress(
        city: String,
        complement: String,
        district: String,
        number: String,
        state: String,
        street: String,
        zipCode: String
    ) = if (addressRepository.insertAddress(
            city,
            complement,
            district,
            number,
            state,
            street,
            zipCode
        )
    ) {
        InsertAddressState.Success
    } else {
        InsertAddressState.Error(listOf(InsertAddressErrorType.SERVER_UNAVAILABLE))
    }

    private fun checkFields(
        city: String,
        district: String,
        number: String,
        state: String,
        street: String,
        zipCode: String
    ): List<InsertAddressErrorType> {
        val errors: MutableList<InsertAddressErrorType> = mutableListOf()
        if (city.isBlank()) {
            errors.add(InsertAddressErrorType.INVALID_CITY)
        }
        if (district.isBlank()) {
            errors.add(InsertAddressErrorType.INVALID_DISTRICT)
        }
        if (number.isBlank()) {
            errors.add(InsertAddressErrorType.INVALID_NUMBER)
        }
        if (state.isBlank()) {
            errors.add(InsertAddressErrorType.INVALID_STATE)
        }
        if (street.isBlank()) {
            errors.add(InsertAddressErrorType.INVALID_STREET)
        }
        if (zipCode.isBlank()) {
            errors.add(InsertAddressErrorType.INVALID_ZIP_CODE)
        }
        return errors
    }
}
