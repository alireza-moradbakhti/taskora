package com.example.taskora.feature_auth.domain.util


class InvalidEmailException(message: String = "Invalid email format") : Exception(message)
class EmptyEmailException(message: String = "Email cannot be empty") : Exception(message)
class EmptyPasswordException(message: String = "Password cannot be empty") : Exception(message)