package github.com.hukuta94.truckmonitoring.core.domain.common

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class VINTest : StringSpec({

    "must create VIN when valid 17-character uppercase value" {
        val vin = VIN.from("XTA12345678901234")
        vin.value shouldBe "XTA12345678901234"
    }

    "must normalize lowercase input to uppercase" {
        val vin = VIN.from("xta12345678901234")
        vin.value shouldBe "XTA12345678901234"
    }

    "must trim leading and trailing spaces" {
        val vin = VIN.from("  XTA12345678901234  ")
        vin.value shouldBe "XTA12345678901234"
    }

    "must allow VINs with length 11" {
        val vin = VIN.from("AB123456789")
        vin.value shouldBe "AB123456789"
    }

    "must reject VIN shorter than 11 characters" {
        val exception = shouldThrow<IllegalArgumentException> {
            VIN.from("ABC123")
        }
        exception.message shouldBe "VIN ABC123 is invalid. Valid VIN must be 11-17 chars, only A-Z and 0-9, without I, O, Q"
    }

    "must reject VIN longer than 17 characters" {
        val exception = shouldThrow<IllegalArgumentException> {
            VIN.from("AB1234567890123456")
        }
        exception.message shouldBe "VIN AB1234567890123456 is invalid. Valid VIN must be 11-17 chars, only A-Z and 0-9, without I, O, Q"
    }

    "must reject VIN with forbidden characters I, O, Q" {
        listOf("ABICD12345678", "ABOCD12345678", "ABQCD12345678").forEach { invalidVin ->
            val exception = shouldThrow<IllegalArgumentException> {
                VIN.from(invalidVin)
            }
            exception.message shouldBe "VIN $invalidVin is invalid. Valid VIN must be 11-17 chars, only A-Z and 0-9, without I, O, Q"
        }
    }

    "must reject VIN with special characters or spaces" {
        listOf("AB#123456789", "AB 123456789", "AB_123456789").forEach { invalidVin ->
            val exception = shouldThrow<IllegalArgumentException> {
                VIN.from(invalidVin)
            }
            exception.message shouldBe "VIN $invalidVin is invalid. Valid VIN must be 11-17 chars, only A-Z and 0-9, without I, O, Q"
        }
    }

    "must reject VIN with non-Latin letters" {
        val exception = shouldThrow<IllegalArgumentException> {
            VIN.from("АБВ123456789") // кириллические символы
        }
        exception.message shouldBe "VIN АБВ123456789 is invalid. Valid VIN must be 11-17 chars, only A-Z and 0-9, without I, O, Q"
    }

    "must be equal when values are the same ignoring case" {
        val vin1 = VIN.from("XTA12345678901234")
        val vin2 = VIN.from("xta12345678901234")
        vin1 shouldBe vin2
    }
})
