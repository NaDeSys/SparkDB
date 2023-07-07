# SparkDB
[![GitHub forks](https://img.shields.io/github/forks/NaDeSys/SparkDB)](https://github.com/NaDeSys/SparkDB/network)
[![GitHub stars](https://img.shields.io/github/stars/NaDeSys/SparkDB)](https://github.com/NaDeSys/SparkDB/stargazers)
[![GitHub issues](https://img.shields.io/github/issues/NaDeSys/SparkDB)](https://github.com/NaDeSys/SparkDB/issues)
[![GitHub issues](https://img.shields.io/badge/License-AGPLv3.0-blue)](https://github.com/NaDeSys/SparkDB/blob/main/LICENSE.txt)
<br>CSV-to-database-structure project that aims for: 
- write-on-memory connection instead of network connection
- Simple usage and commands
- Allowing data analysis for CSV raw data

## Test it!
You can run `$ javac main.java && java main` to test SparkDB

## Documentation
You can find javadoc [here](https://nadesys.github.io/SparkDBDoc/SparkDB.html). A code example is *src/main.java*. See also *doc/*

## Philosophy
There exists a lot of database solutions and types, such as (MY/MS)SQL, Redis, Postegres, etc.. . These database solutions are often an overkill for small projects and data manipulation. SparkDB is an abstract CSV-in-memory project, where every SparkDB object is a table. SparkDB offers a bloatware-free solution with an easy and minimal architecture.<br><br>
With some coding, you can tame SparkDB to behave as a replacement for SQL, redis, or other database solutions. Write your own relational database, or in-memory cache with ease.<br><br>
In short,
- SparkDB is an abstract building block for database solutions.
- SparkDB is used for scenarios where SQL-based/redis solutions are an overkill.
- SparkDB does not have default Network-based communication, but it uses function calls.
- SparkDB is NOT a relational database, but you can make your own relational implementation based on SparkDB.
- SparkDB does not use a query language, but normal function calls that are understandable by any Java developer.
- SparkDB is a minimal solution. Most of the time you have to use it as a building block to make a distributed database.
- SparkDB only uses pure java. No frameworks or other libraries are required.

Note: SparkDB table has a maximum of 2.147 billion rows. If your usecase requires more rows, try to divide/shard the data.

## Contributors
[Morad Abdelrasheed](https://github.com/Zelakolase) - [Youssef Hegazy (Indirect Suggestion, accepted)](https://github.com/hegzploit) - [Omar Mohamed Khallaf)](https://github.com/OmarMohamedKhallaf)