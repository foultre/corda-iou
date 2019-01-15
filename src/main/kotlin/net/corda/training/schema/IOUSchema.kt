package net.corda.training.schema

import net.corda.core.schemas.MappedSchema
import net.corda.core.schemas.PersistentState
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import org.hibernate.annotations.Type
import net.corda.core.serialization.CordaSerializable
import javax.persistence.Index

/**
 * The family of schemas for IOUState.
 */
object IOUSchema

/**
 * An IOUState schema.
 */
object IOUSchemaV1 : MappedSchema(
        schemaFamily = IOUSchema.javaClass,
        version = 1,
        mappedTypes = listOf(PersistentIOU::class.java)) {
    @Entity
    @Table(name = "iou_states",
            indexes = arrayOf(Index(name = "ioustates_lender", columnList = "lender"), Index(name = "ioustate_value", columnList = "value")))
    class PersistentIOU(
            @Column(name = "lender")
            var lenderName: String,

            @Column(name = "borrower")
            var borrowerName: String,

            @Column(name = "Currency")
            var currency: String,

            @Column(name = "value")
            var value: Long,

            @Column(name = "Paid_Currency")
            var paidCurrency: String,

            @Column(name = "Paid_Value")
            var paid: Long,

            @Column(name = "linear_id")
            var linearId: UUID
    ) : PersistentState(){
        constructor(): this("","","",0,"",0, UUID.randomUUID())
    }
}