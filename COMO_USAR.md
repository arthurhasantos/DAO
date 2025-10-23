# 📖 Como Usar - Guia Rápido

## 🚀 Execução Simples

### 1. Compilar o projeto
```bash
executar.bat
```
Isso compila o projeto e deixa tudo pronto para usar.

### 2. Abrir o console web do banco de dados
```bash
abrir-console-h2.bat
```
Isso abre o navegador para você visualizar e editar o banco de dados.

**Configuração do H2 Console:**
- **JDBC URL**: `jdbc:h2:./data/clientedb`
- **User**: `sa`
- **Password**: (deixe vazio)

---

## 💻 Exemplos de Código Java

### Criar Cliente (CREATE)
```java
ClienteDAO dao = new ClienteDAOImpl();

Cliente cliente = new Cliente("João Silva", "joao@email.com", "(11) 98765-4321", "123.456.789-01");
cliente = dao.create(cliente);

System.out.println("Cliente criado com ID: " + cliente.getId());
```

### Buscar Cliente (READ)
```java
// Por ID
Cliente cliente = dao.findById(1L);

// Por CPF
Cliente cliente = dao.findByCpf("123.456.789-01");

// Por Nome (busca parcial)
List<Cliente> clientes = dao.findByNome("Silva");

// Listar todos
List<Cliente> todos = dao.findAll();
```

### Atualizar Cliente (UPDATE)
```java
Cliente cliente = dao.findById(1L);
cliente.setTelefone("(11) 99999-9999");
cliente.setEmail("novo@email.com");

boolean sucesso = dao.update(cliente);
```

### Deletar Cliente (DELETE)
```java
boolean sucesso = dao.delete(1L);
```

---

## 🗂️ Estrutura Básica

```
src/main/java/com/exemplo/
├── model/Cliente.java           → Entidade (dados do cliente)
├── dao/ClienteDAO.java          → Interface (métodos disponíveis)
├── dao/impl/ClienteDAOImpl.java → Implementação (código JDBC)
└── database/DatabaseConnection  → Conexão com H2
```

---

## 🎯 Para Que Serve Cada Arquivo

| Arquivo | Função |
|---------|--------|
| `executar.bat` | Compila o projeto |
| `abrir-console-h2.bat` | Abre interface web do banco de dados |
| `TESTES_SQL.md` | Lista de comandos SQL prontos para copiar |

---

## 🔧 Customizações Simples

### Adicionar novo campo no Cliente

**1. Edite `Cliente.java`**
```java
private String endereco; // Novo campo

public String getEndereco() { return endereco; }
public void setEndereco(String endereco) { this.endereco = endereco; }
```

**2. Edite `DatabaseConnection.java`**
```java
// Adicione na criação da tabela:
"endereco VARCHAR(200), " +
```

**3. Edite `ClienteDAOImpl.java`**
```java
// No INSERT:
stmt.setString(5, cliente.getEndereco());

// No UPDATE:
stmt.setString(5, cliente.getEndereco());

// No extractClienteFromResultSet:
cliente.setEndereco(rs.getString("endereco"));
```

---

## 🐛 Problemas Comuns

### Erro ao compilar
```bash
# Verifique se tem Java e Maven instalados:
java -version
mvn -version
```

### Banco de dados travado
```bash
# Feche o console H2 e delete a pasta data:
rmdir /s data
```

### Erro "Table already exists"
```bash
# Delete a pasta data para recomeçar:
rmdir /s data
```

---

## 📚 Documentação Completa

- **[README.md](README.md)** - Visão geral do projeto
- **[TESTES_SQL.md](TESTES_SQL.md)** - 60+ exemplos de SQL prontos
- **[COMO_USAR.md](COMO_USAR.md)** - Este arquivo

---

## 🎓 Próximos Passos

1. Execute `executar.bat` para compilar o projeto
2. Abra `abrir-console-h2.bat` para visualizar o banco
3. Copie comandos SQL do `TESTES_SQL.md` e teste no console
4. Use as classes DAO no seu próprio código
5. Adicione novos campos ou crie novas entidades

**Pronto! Agora é só experimentar! 🚀**
