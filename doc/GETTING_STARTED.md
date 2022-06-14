# Getting Started
Before continuing, Please put **SparkDB.java** inside the package, or in *src/* directory in your project.
- [Getting Started](#getting-started)
  * [An example database creation with operations](#an-example-database-creation-with-operations)
    + [Making a database](#making-a-database)
    + [Add, get, modify, or delete entries](#add-get-modify-or-delete-entries)
  * [Read existing database, and bulk operations](#read-existing-database-and-bulk-operations)


## An example database creation with operations
### Making a database
- Let's start by creating a SparkDB object!
  ```java
  SparkDB db = new SparkDB();
  ```
- Then, add some headers, these headers are also called column names. This command will initalize the desired number of columns.
  ```java
  db.create(new ArrayList<String>() {{
    add("user");
    add("pass");
  }});
  ```
Congratulations! You made your very first database!

### Add, get, modify, or delete entries
- Let's add our very first entry.
  ```java
  db.add(new HashMap<String, String>() {{
    put("user","john");
    put("pass","123");
  }});
  ```
  The key of the hashmap is the column name, and the value of the hashmap is the value.
- Is the password not secure enough? How about we modify it?
  ```java
  db.modify(new HashMap<String, String>() {{
    put("user","john"); // <-- Points to all entries that have 'john' in the 'user' column
    }}, new HashMap<String, String>() {{
    put("pass","456"); // <-- The change
  }});
  ```
- Get the password of john
  ```java
  db.get(new HashMap<String, String>() {{
    put("user","john"); // <-- Points to all entries that have 'john' in the 'user' column
    }},"pass") // <-- Target column
    .get(0); // <-- Get the first password, if there is multiple johns in the database
  ```
- Delete entry we just created
  ```java
  db.delete(new HashMap<String, String>() {{
    put("user","john"); // <-- Points to all entries that have 'john' in the 'user' column
  }});
  ```

- View the database in CSV format
  ```java
  System.out.println(db);
  ```

## Read existing database, and bulk operations
  Let's read **db.txt**. This database contains monthly prices for carrots and tomatoes in a single year.
- Read the database
  ```java
  db.readFromFile("./db.txt");
  ```
- Get all prices of carrots in order, and print them
  ```java
  System.out.println(db.get(new HashMap<String, String>() {{
    put("product","carrots"); // <-- Points to all entries that have 'carrots' in the 'product' column
  }}, "price")); // <-- Target column
  ```
- Get all prices of products in January, and print them
  ```java
  System.out.println(db.get(new HashMap<String, String>() {{
    put("month","1"); // <-- Points to all entries that have '1' in the 'month' column
  }}, "price")); // <-- Target column
  ```
- Get all prices regardless of product or month, and print them
  ```java
  System.out.println(db.getColumn("price")); // <-- Gets the whole column
  ```
- Get the first entry in the file
  ```java
  db.get(0); // <-- Get index 0
  ```
