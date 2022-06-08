# SparkDB
[![GitHub forks](https://img.shields.io/github/forks/NaDeSys/SparkDB)](https://github.com/NaDeSys/SparkDB/network)
[![GitHub stars](https://img.shields.io/github/stars/NaDeSys/SparkDB)](https://github.com/NaDeSys/SparkDB/stargazers)
[![GitHub issues](https://img.shields.io/github/issues/NaDeSys/SparkDB)](https://github.com/NaDeSys/SparkDB/issues)
[![GitHub issues](https://img.shields.io/badge/License-AGPLv3.0-blue)](https://github.com/NaDeSys/SparkDB/blob/main/LICENSE.txt)
<br>CSV-to-database-structure project that aims for: 
- Write-on-disk connection instead of network connection
- Simple usage and commands
- Allowing data analysis for CSV raw data

## Test it!
You can run `$ javac main.java && java main` to test SparkDB

## But why?
SQL statements are not an optimal way to communicate with a database. If not given enough sanitization, [it can lead to security issues](https://en.wikipedia.org/wiki/SQL_injection).<br>
We have decided to invent the wheel for the good. You must learn SQL in order to work with it, SparkDB is a lot simpler. You don't have to learn SparkDB in order to work with it! SparkDB is perfect for small to mid-size databases for internal systems that need to be secure enough. 

## Documentation
You can find the documentation in *doc/index.html*. A code example is *src/main.java*

## TO-DO
- [ ] JSON Parsing beside CSV
- [ ] Performance and memory usage check and improvements

## Contributors
!!! This section is only modified by NaDeSys. !!!<br>
[Morad Abdelrasheed](https://github.com/Zelakolase)

## Stargazers over time
[![Stargazers over time](https://starchart.cc/NaDeSys/SparkDB.svg)](https://starchart.cc/NaDeSys/SparkDB)

