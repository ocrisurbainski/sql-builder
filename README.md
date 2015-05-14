#SQL-Builder

[![Build Status](https://drone.io/github.com/CristianUrbainski/sql-builder/status.png)](https://drone.io/github.com/CristianUrbainski/sql-builder/latest)

Geração de código SQL por objetos java.

Ex:

Para gerar o sql abaixo:

```sql
select livro.id, livro.ds_nome, livro.nr_anopublicacao from livro
```
usando o projeto ficaria assim:

```java
SQLBuilder sqlBuilder = new SQLBuilder(Livro.class);
final String sqlGerado = sqlBuilder.buildSQL();
```

Para mais exemplos verifique o fontes de teste unitários do projeto.

