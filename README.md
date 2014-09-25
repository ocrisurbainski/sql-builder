#SQL-Builder
===========

Geração de código SQL por objetos javas.

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

