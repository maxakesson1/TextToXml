import java.io.File

fun main(args: Array<String>) {
    val inputFile = File(args[0]).readText()
    val outputFile = if (args.size == 2) File(args[1]) else File("output.xml")

    val lines = inputFile.split("\n").toTypedArray()
    val generator = XmlGenerator(lines)
    generator.generateXml()
    generator.writeXml(outputFile)
}