# Необходимое предустановленное ПО #
* JDK 1.8 и выше

# Запуск программы #
* склонируйте репозиторий
* ```cd Lab1/Lab1/```
* скомпилируйте командой
```javac -cp libs/ChargingModule.jar src/main/Main.java src/main/parser/CSVParser.java -d target/```
* запустите командой
```java -cp libs/ChargingModule.jar;target main.Main```