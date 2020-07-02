package it.mmilani.pokedex.api.models

data class Error (
    var error : Throwable
) : BaseResponse()