# CRUD de Cliente com Padrão DAO e Banco H2

Este projeto demonstra a implementação de um CRUD (Create, Read, Update, Delete) básico para gerenciamento de clientes, utilizando o padrão de design **DAO (Data Access Object)** e o banco de dados **H2** do Java.

## 🏗️ Arquitetura

O projeto segue o padrão DAO, que separa a lógica de acesso a dados da lógica de negócio:

```
src/main/java/com/exemplo/
├── model/
│   └── Cliente.java              # Entidade Cliente
├── dao/
│   ├── GenericDAO.java           # Interface genérica para CRUD
│   ├── ClienteDAO.java           # Interface específica para Cliente
│   └── impl/
│       └── ClienteDAOImpl.java   # Implementação do ClienteDAO
├── database/
│   └── DatabaseConnection.java   # Gerenciamento de conexão com H2
└── Main.java                     # Inicializa o banco de dados
```

## 🚀 Funcionalidades

### Operações CRUD Básicas:
- **Create**: Inserir novo cliente
- **Read**: Buscar cliente por ID, CPF, nome ou listar todos
- **Update**: Atualizar dados do cliente
- **Delete**: Remover cliente

### Métodos Adicionais:
- `findByCpf(String cpf)`: Busca cliente pelo CPF
- `findByNome(String nome)`: Busca clientes por nome (busca parcial)

## 📋 Pré-requisitos

- Java 11 ou superior
- Maven 3.6 ou superior

**Para instalar em outro PC**, veja o guia completo: **[INSTALACAO.md](INSTALACAO.md)**

**Verificar se seu PC está pronto:**
```bash
verificar-requisitos.bat
```

## 🔧 Como Executar

### Passo 1: Executar o Projeto
```bash
executar.bat
```
Isso compila o projeto **E** cria a tabela CLIENTE automaticamente!

### Passo 2: Visualizar o Banco
```bash
abrir-console-h2.bat
```
Abre o console web para visualizar e testar o banco de dados.

### Passo 3: Usar as Classes DAO
Integre as classes DAO no seu próprio código.

## 🌐 Console Web H2 Database

Para visualizar e manipular o banco de dados através de uma interface web:

```bash
# Windows
abrir-console-h2.bat

# Linux/Mac
chmod +x abrir-console-h2.sh
./abrir-console-h2.sh
```

Isso abrirá automaticamente o navegador em `http://localhost:8082` onde você poderá:
- Visualizar todos os clientes cadastrados
- Executar comandos SQL diretamente
- Testar INSERT, UPDATE, DELETE e SELECT
- Ver a estrutura completa da tabela

📚 **Veja o arquivo [TESTES_SQL.md](TESTES_SQL.md)** para exemplos completos de comandos SQL!

## 💾 Banco de Dados H2

O projeto está configurado para usar o H2 em modo **embedded** (arquivo local). O banco de dados será criado automaticamente na pasta `data/` na raiz do projeto.

### Configurações:
- **URL**: `jdbc:h2:./data/clientedb`
- **Usuário**: `sa`
- **Senha**: (vazia)

### Para usar H2 em memória:
Edite o arquivo `DatabaseConnection.java` e descomente a linha:
```java
private static final String URL = "jdbc:h2:mem:clientedb";
```

## 📊 Estrutura da Tabela Cliente

```sql
CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20),
    cpf VARCHAR(14) UNIQUE NOT NULL
);
```

## 🎯 Padrão DAO

O padrão DAO (Data Access Object) oferece várias vantagens:

1. **Separação de Responsabilidades**: Isola a lógica de acesso a dados
2. **Facilidade de Manutenção**: Mudanças no banco não afetam a lógica de negócio
3. **Testabilidade**: Facilita a criação de testes unitários
4. **Reutilização**: Código mais organizado e reutilizável

### Camadas do Projeto:
- **Model**: Representa as entidades do domínio
- **DAO**: Interface que define as operações de persistência
- **DAO Implementation**: Implementação concreta usando JDBC
- **Database**: Gerenciamento de conexões

## 📝 Exemplo de Uso

```java
// Criar instância do DAO
ClienteDAO clienteDAO = new ClienteDAOImpl();

// Criar novo cliente
Cliente cliente = new Cliente("João Silva", "joao@email.com", "(11) 98765-4321", "123.456.789-01");
cliente = clienteDAO.create(cliente);

// Buscar cliente por ID
Cliente encontrado = clienteDAO.findById(cliente.getId());

// Atualizar cliente
encontrado.setTelefone("(11) 99999-9999");
clienteDAO.update(encontrado);

// Listar todos os clientes
List<Cliente> todos = clienteDAO.findAll();

// Deletar cliente
clienteDAO.delete(cliente.getId());
```

## 🛠️ Tecnologias Utilizadas

- **Java 11**
- **H2 Database 2.2.224**
- **JDBC** (Java Database Connectivity)
- **Maven** (Gerenciamento de dependências)

## 📖 Aprendizados

Este projeto demonstra:
- Implementação do padrão DAO
- Conexão e manipulação de banco de dados com JDBC
- Uso do banco H2 em modo embedded
- Operações CRUD completas
- Boas práticas de organização de código Java
- Tratamento de exceções SQL
- Uso de PreparedStatement para segurança

## 📚 Documentação Adicional

Este projeto inclui documentação completa:

- **[README.md](README.md)** - Este arquivo, visão geral do projeto
- **[INSTALACAO.md](INSTALACAO.md)** - Guia de instalação em outro PC
  - Requisitos do sistema
  - Passo a passo completo
  - Checklist de instalação
- **[PROBLEMAS_COMUNS.md](PROBLEMAS_COMUNS.md)** - 🆕 Soluções para erros frequentes
  - 10 problemas mais comuns
  - Soluções passo a passo
  - Checklist de diagnóstico
  - Dicas preventivas
- **[COMO_USAR.md](COMO_USAR.md)** - Guia prático com exemplos de código Java
- **[TESTES_SQL.md](TESTES_SQL.md)** - Exemplos de comandos SQL para testar no Console H2
  - INSERT, UPDATE, DELETE, SELECT
  - Consultas avançadas
  - Transações e validações
  - Dados de teste
  - Estatísticas e backup

## 📦 Arquivos Úteis

- `verificar-requisitos.bat` - Verifica se Java e Maven estão instalados
- `diagnostico.bat` - Diagnóstico completo do projeto
- `executar.bat` - Compila o projeto e cria a tabela CLIENTE
- `abrir-console-h2.bat` - Abre o console web do H2
- `pom.xml` - Configuração Maven com dependências
- `.gitignore` - Ignora arquivos desnecessários no Git

## 📄 Licença

Este projeto é de código aberto e está disponível para fins educacionais.