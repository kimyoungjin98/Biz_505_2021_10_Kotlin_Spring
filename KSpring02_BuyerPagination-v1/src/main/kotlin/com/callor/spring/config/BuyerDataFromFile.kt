package com.callor.spring.config

import com.callor.spring.ConfigData.Companion.RND
import com.callor.spring.model.Buyer
import com.callor.spring.repository.BuyerRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.context.config.ConfigData
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.function.Consumer
import javax.transaction.Transactional


@SpringBootConfiguration
class BuyerDataFromFile(val bDao:BuyerRepository) {

    /*
     * @Value()
     * .properties 또는 *.yml 파일에 설정된 속성(com.callor.spring.student-file) 을
            참조하여 속성에 설정된 값(주로 문자열)을 가져와서 dataFile 변수에 담아라
     */
    @Value("\${com.callor.spring.student-file}")
    private val dataFile:String? = null
    // lateinit var dataFile:String? = null

    // 00126:매재찬:경제학:2:1:울산광역시 울주군 문수로382:010-6239-1705
    private val 학번 = 0
    private val 이름 = 1
    private val 학과 = 2
    private val 학년 = 3
    private val 반 = 4
    private val 주소 = 5
    private val 전화번호 = 6

    // private val logger = LoggerFactory.getLogger(BuyerDataFromFile::class.java)

    @Bean
    @Transactional
    fun dataFromFile():CommandLineRunner {
        logger().debug("Data File: {}", dataFile)

        // dataFile 변수를 null 허용변수로 만들었기 때문에 다른 변수를 담을 때는 아래와 같은 키워드를 사용
        // dataFile 변수에 담긴 값을 {} 부분으로 전달(let, 할당)하고
        // ClassPathResource() 함수의 매개변수로 전달
        // ClassPathResource() 함수가 return하는 값을 변수 file에 담아라

        // val file = ClassPathResource("student.txt")
        // val file = ClassPathResource(dataFile)

        // 복잡하게 쓰는 이유.. : NullPointException 방지 ?
        val file = dataFile?.let { ClassPathResource(it) }
        
        // ClassPathResource() 함수를 이용하여 Resources 폴더에 저장된 파일 정보를 가져오면
        // 다음과 같은 속성값을 취할 수 있다
        logger().debug("파일 객체 {}", file?.file.toString()) // getFile()
        logger().debug("파일 이름 {}", file?.filename.toString()) // getFileName()
        logger().debug("InputStream {}", file?.inputStream.toString()) // getInputStream()
        logger().debug("파일 저장경로 객체 {}", file?.path.toString()) // getPath()
        logger().debug("파일 URL {}", file?.url.toString()) // getURL()
        logger().debug("파일 URI {}", file?.uri.toString()) // getURI()

        /**
         * java 1.8 이상에서 사용하는 text file 읽기 코드
         * file의 uri 정보를 Paths 객체에 주입하여 Path 객체를 만든다
         * Files 클래스의 readAllLines() 함수에 Path 객체를 주입하면
         * text 파일의 전체 내용을 한 라인씩 잘라서 List 데이터로 생성해준다
         */
        // file이 정상적으로 loading 되었을 경우 file의 uri를 사용해서 Path를 만들어라
        val path: Path = Paths.get(file?.uri)
        val fileList:List<String> = Files.readAllLines(path)

        fileList.forEach(Consumer { str:String -> logger().debug(str) })

        val managerList = fileList.map {
            val lines = it.split(":")
            // { 이름: "홍길동", 전화번호: "010-1234-5678" } 형식의 데이터 생성해서
            // managerList에 넣어라
            mapOf(이름 to lines[이름], 전화번호 to lines[전화번호])
        }
        val buyerList = fileList.mapIndexed {
            index, str ->
            val lines = str.split(":")
            val userid = String.format("B%03d", index + 1)

            val mSize = managerList.size
            val mIndex = RND.nextInt(mSize)
            val manager = managerList[mIndex]

            Buyer(
                userid = userid,
                name = lines[이름],
                tel = lines[전화번호],
                address = lines[주소],
                manager = manager[이름],
                man_tel = manager[전화번호]
            )
        }
        bDao.saveAll(buyerList)


        return CommandLineRunner {
            logger().debug(it.toString())
        }
    }

}