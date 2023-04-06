import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import java.io.File

class XmlGenerator (private val lines : Array<String>) {
    private var people = People()
    private val xmlMapper = XmlMapper()
    fun writeXml(outputFile: File){
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, false)
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT)
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        val xml = xmlMapper.writeValueAsString(people)
        println(xml)
        outputFile.writeText(xml)
    }
    fun generateXml(){
        var inPerson = false
        var inFamily = false
        var currentPerson = Person()
        var currentFamily = Family()
        for (line in lines){
            val lineArray = line.split("|")
            when (lineArray[0]){
                "P" ->{
                    if (inFamily){
                        currentPerson.family.add(currentFamily)
                        currentFamily = Family()
                        inFamily = false
                    }
                    if (inPerson){
                        people.person.add(currentPerson)
                        currentPerson = Person()
                    }
                    currentPerson.firstname = lineArray[1].trim()
                    currentPerson.lastname = lineArray[2].trim()
                    inPerson = true
                }
                "T" -> {
                    var phone = Phone(lineArray[1].trim(), if (lineArray.size >= 3) lineArray[2].trim() else null)
                    if (inFamily) currentFamily.phone = phone else currentPerson.phone = phone
                }
                "A" -> {
                    var address = Address(lineArray[1].trim(), if (lineArray.size >= 3) lineArray[2].trim() else null, if (lineArray.size >= 4) lineArray[3].trim() else null)
                    if (inFamily) currentFamily.address = address else currentPerson.address = address
                }
                "F" -> {
                    if (inFamily){
                        currentPerson.family.add(currentFamily)
                        currentFamily = Family()
                    }
                    inFamily = true
                    currentFamily.name = lineArray[1].trim()
                }
            }
        }
        if (inFamily) currentPerson.family.add(currentFamily)
        if (inPerson) people.person.add(currentPerson)
    }
}