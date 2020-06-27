package com.example.task3.repository

import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = arrayOf("com.example.task3.repository"))
class RepositoryConfig {
}