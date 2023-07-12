package com.ataerdal.apptern201homework.base

interface Mapper<T, DomainModel> {
    fun toDomain(t: T): DomainModel
    fun fromDomain(domain: DomainModel): T
}