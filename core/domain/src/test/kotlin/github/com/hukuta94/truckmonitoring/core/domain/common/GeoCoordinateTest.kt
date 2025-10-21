package github.com.hukuta94.truckmonitoring.core.domain.common

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.bigDecimal
import io.kotest.property.checkAll
import java.math.BigDecimal

class GeoCoordinateTest : StringSpec({

    "must create object when latitude and longitude are within valid ranges" {
        val validLatitudes = Arb.bigDecimal(BigDecimal("-90.0000"), BigDecimal("90.0000"))
        val validLongitudes = Arb.bigDecimal(BigDecimal("-180.0000"), BigDecimal("180.0000"))

        checkAll(
            validLatitudes, validLongitudes,
        ) { latitude, longitude ->
            val geo = GeoCoordinate.from(latitude, longitude)
            assertSoftly {
                geo.latitude shouldBe latitude
                geo.longitude shouldBe longitude
            }
        }
    }

    "must allow latitude and longitude at exact lower boundaries" {
        val geo = GeoCoordinate.from(BigDecimal("-90"), BigDecimal("-180"))
        geo.latitude shouldBe BigDecimal("-90")
        geo.longitude shouldBe BigDecimal("-180")
    }

    "must allow latitude and longitude at exact upper boundaries" {
        val geo = GeoCoordinate.from(BigDecimal("90"), BigDecimal("180"))
        geo.latitude shouldBe BigDecimal("90")
        geo.longitude shouldBe BigDecimal("180")
    }

    "must reject latitude less than -90" {
        val exception = shouldThrow<IllegalArgumentException> {
            GeoCoordinate.from(BigDecimal("-90.0001"), BigDecimal("0"))
        }
        exception.message shouldBe "Invalid latitude value: -90.0001. Valid latitude range: ${BigDecimal("-90")..BigDecimal("90")}"
    }

    "must reject latitude greater than 90" {
        val exception = shouldThrow<IllegalArgumentException> {
            GeoCoordinate.from(BigDecimal("90.1"), BigDecimal("0"))
        }
        exception.message shouldBe "Invalid latitude value: 90.1. Valid latitude range: ${BigDecimal("-90")..BigDecimal("90")}"
    }

    "must reject longitude less than -180" {
        val exception = shouldThrow<IllegalArgumentException> {
            GeoCoordinate.from(BigDecimal("0"), BigDecimal("-180.0001"))
        }
        exception.message shouldBe "Invalid longitude value: -180.0001. Valid longitude range: ${BigDecimal("-180")..BigDecimal("180")}"
    }

    "must reject longitude greater than 180" {
        val exception = shouldThrow<IllegalArgumentException> {
            GeoCoordinate.from(BigDecimal("0"), BigDecimal("180.0001"))
        }
        exception.message shouldBe "Invalid longitude value: 180.0001. Valid longitude range: ${BigDecimal("-180")..BigDecimal("180")}"
    }

    "must create equal objects for identical coordinates" {
        val g1 = GeoCoordinate.from(BigDecimal("10.0"), BigDecimal("20.0"))
        val g2 = GeoCoordinate.from(BigDecimal("10.0"), BigDecimal("20.0"))
        g1 shouldBe g2
    }

    "must create different objects for different coordinates" {
        val g1 = GeoCoordinate.from(BigDecimal("10.0"), BigDecimal("20.0"))
        val g2 = GeoCoordinate.from(BigDecimal("10.0"), BigDecimal("21.0"))
        g1 shouldNotBe g2
    }

    "must support negative and fractional coordinates" {
        val geo = GeoCoordinate.from(BigDecimal("-33.865143"), BigDecimal("151.209900"))
        geo.latitude shouldBe BigDecimal("-33.865143")
        geo.longitude shouldBe BigDecimal("151.209900")
    }
})

