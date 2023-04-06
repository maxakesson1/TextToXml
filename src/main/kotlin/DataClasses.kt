import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "people")
data class People(
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "person")
    val person: MutableList<Person> = mutableListOf()
)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Person(
    @JacksonXmlProperty(localName = "firstname")
    var firstname: String = "",
    @JacksonXmlProperty(localName = "lastname")
    var lastname: String = "",
    @JacksonXmlProperty(localName = "address")
    var address: Address? = null,
    @JacksonXmlProperty(localName = "phone")
    var phone: Phone? = null,
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "family")
    val family: MutableList<Family> = mutableListOf()
)
data class Address(
    @JacksonXmlProperty(localName = "street")
    val street: String? = null,
    @JacksonXmlProperty(localName = "city")
    val city: String? = null,
    @JacksonXmlProperty(localName = "zip")
    val zip: String? = null
)
data class Phone(
    @JacksonXmlProperty(localName = "mobile")
    val mobile: String? = null,
    @JacksonXmlProperty(localName = "home")
    val home: String? = null
)
data class Family(
    @JacksonXmlProperty(localName = "name")
    var name: String = "",
    @JacksonXmlProperty(localName = "address")
    var address: Address? = null,
    @JacksonXmlProperty(localName = "phone")
    var phone: Phone? = null
)