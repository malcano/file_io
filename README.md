[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=9337709&assignment_repo_type=AssignmentRepo)
## 제출 기한

* 2022/11/21 23:59

## 과제 목적

아래 요구사항을 구현한 `WordCounter.java`와 `FileSplitter.java`를 생성 및 작성한다.

## 과제 내용

### 1. WordCounter.java (5 pts)

* 리눅스 유틸리티 커맨드 `wc`를 모방한 Java 프로그램 `WordCounter.java`를 구현한다.
* `wc`는 입력 파일을 읽어서 라인수(# of lines), 단어수(# of words), 문자수(# of characters)를 출력해주는 프로그램이다.
* 위 3가지 항목의 출력 여부를 커맨드라인 옵션(-l, -w, -c)으로 결정할 수 있으며, 옵션이 아무것도 없을 시 3가지 항목이 모두 출력된다.
* 실행 방법은 ``java WordCounter [options] [input_file]`` 이다.
* 출력 양식은 ``#_of_lines|#_of_words|#_of_characters|input_file`` 이다.
* 실행 및 출력 결과의 예제는 다음과 같다.
```
$ javac WordCounter.java
$ java WordCounter bible.txt
30383|766111|4047392|bible.txt
$ java WordCounter -w bible.txt
766111|bible.txt
$ java WordCounter -lw bible.txt
30383|766111|bible.txt
$ java WordCounter -wc bible.txt
766111|4047392|bible.txt
```
* 옵션을 준 순서나 가지수와 무관하게, 라인수|단어수|문자수 의 출력 순서를 유지하는 것에 유의한다.
* 인자의 개수가 모자르거나, 유효하지 않을 때는 다음을 출력한다.
```
$ java WordCounter
usage: java WordCounter [options] [input_file]
$ java WordCounter -w
usage: java WordCounter [options] [input_file]
```

### 2. FileSplitter.java (5 pts)

* 입력 텍스트 파일을 정해진 라인수대로 분할하는 프로그램 `FileSplitter.java`를 구현한다.
* 실행 양식은 `java FileSplitter [input_file] [output_directory] [number_of_lines]` 이다.
* 출력 파일은 `frag1.txt, frag2.txt, frag3.txt, ...` 과 같이 라인수 단위로 개별 생성된다.
* 출력 파일들은 `output_directory`로 지정된 경로에 저장되며, 만약 해당 디렉토리가 없을 시 생성하는 코드를 구현한다.
* 예를들어 30383개의 라인수를 가진 입력파일인 `bible.txt`을 10000개씩 분리하여 `output` 디렉토리에 저장할 때의 실행 결과는 다음과 같다.
```
$ javac FileSplitter.java
$ java FileSplitter bible.txt output 10000
$ wc -l output/*
   10000 output/frag1.txt
   10000 output/frag2.txt
   10000 output/frag3.txt
     383 output/frag4.txt
   30383 total
```
* wc 명령어가 없는 Windows 에서는 ``find /c /v "" "output\*"`` 명령어를 이용해 확인해볼 수 있다.
    * (참조: https://stackoverflow.com/questions/50976223/what-is-the-windows-equivalent-of-linux-command-wc-l)
* 커맨드라인 인자의 개수가 충분하지 않거나 유효하지 않을 때 다음을 출력한다.
```
$ java FileSplitter.java
usage: java FileSplitter [input_file] [output_directory] [number_of_lines]
$ java FileSplitter.java bible.txt
usage: java FileSplitter [input_file] [output_directory} [number_of_lines]
```

## Hint

* 커맨드라인에 입력된 인자는 main 메서드의 매개변수에 args에 차례대로 저장된다.
* 텍스트 파일을 한줄 씩 읽기위해서 아래 코드를 사용할 수 있다.
```
//...
BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file_name)));
String line = br.readLine();
//...
```
* 문자열을 공백 단위로 분리하기 위해 아래 메서드를 사용할 수 있다.
```
//...
String str = "Hello World!";
String[] split = str.split("\\s+");
//...
```
* 이 때, split 배열에 분리된 문자열 토큰이 저장된다.

## 컴파일, 테스트 및 제출 방법

* 입력 텍스트 파일로 저장소에 첨부된 파일 `bible.txt`을 이용한다.
* 이번 과제에서는 JUnit을 사용하지 않으며 각 소스파일을 테스트하기 위한 `-Tester.java` 파일을 이용한다.
    * JUnit을 사용하지 않기 때문에 전에 슬라이드로 정리해서 올렸던 VSCode 이용방법으로 테스트가 안될 수 있다.
* 예를들어, `WordCounter.java`를 테스트하기 위해 다음 명령어를 사용한다.
```
$ javac WordCounter.java
$ javac WordCounterTester.java
$ java WordCounterTester
```
* VSCode 이용시 위 메뉴중에서 Terminal -> New Terminal 을 클릭해서 위 명령어를 입력하면 된다.(우클릭 -> Run Java 이용 시 안될 수 있음)
* 제출을 위해 로컬에서 git 명령을 사용하거나, GitHub에서 직접 편집해 제출할 수 있다. 
* 제출 전 코드에 간략하게 주석을 달도록 한다. 

(P.S: 저는 기독교인이 아니며 bible.txt는 단지 테스트용으로 길이가 긴 텍스트 파일을 찾은 것입니다. 제 개인적 견해와 아무 관련없습니다. ^^;)
