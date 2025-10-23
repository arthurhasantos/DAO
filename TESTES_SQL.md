# 🧪 Testes SQL - Banco de Dados Cliente

Este guia contém exemplos de comandos SQL para testar diretamente no **Console H2**.

## 🚀 Como Acessar o Console H2

1. Execute: `abrir-console-h2.bat`
2. Configure a conexão:
   - **JDBC URL**: `jdbc:h2:./data/clientedb`
   - **User Name**: `sa`
   - **Password**: (deixe vazio)
3. Clique em **Connect**

---

## 📊 1. CONSULTAS (SELECT)

### Listar todos os clientes
```sql
SELECT * FROM CLIENTE;
```

### Contar total de clientes
```sql
SELECT COUNT(*) AS total FROM CLIENTE;
```

### Buscar cliente por ID
```sql
SELECT * FROM CLIENTE WHERE ID = 1;
```

### Buscar cliente por CPF
```sql
SELECT * FROM CLIENTE WHERE CPF = '123.456.789-01';
```

### Buscar clientes por nome (contém texto)
```sql
SELECT * FROM CLIENTE WHERE NOME LIKE '%Silva%';
```

### Buscar clientes por email
```sql
SELECT * FROM CLIENTE WHERE EMAIL LIKE '%@email.com';
```

### Listar clientes ordenados por nome
```sql
SELECT * FROM CLIENTE ORDER BY NOME ASC;
```

### Listar apenas nome e telefone
```sql
SELECT ID, NOME, TELEFONE FROM CLIENTE;
```

### Buscar clientes cujo nome começa com 'J'
```sql
SELECT * FROM CLIENTE WHERE NOME LIKE 'J%';
```

---

## ➕ 2. INSERIR DADOS (INSERT)

### Inserir um cliente
```sql
INSERT INTO CLIENTE (NOME, EMAIL, TELEFONE, CPF) 
VALUES ('Carlos Souza', 'carlos@email.com', '(21) 98765-4321', '111.222.333-44');
```

### Inserir múltiplos clientes de uma vez
```sql
INSERT INTO CLIENTE (NOME, EMAIL, TELEFONE, CPF) VALUES
('Ana Paula', 'ana.paula@email.com', '(11) 91111-2222', '222.333.444-55'),
('Roberto Lima', 'roberto@email.com', '(31) 92222-3333', '333.444.555-66'),
('Fernanda Costa', 'fernanda@email.com', '(41) 93333-4444', '444.555.666-77'),
('Lucas Mendes', 'lucas@email.com', '(51) 94444-5555', '555.666.777-88');
```

### Inserir cliente com campos opcionais vazios
```sql
INSERT INTO CLIENTE (NOME, EMAIL, TELEFONE, CPF) 
VALUES ('José Silva', NULL, NULL, '666.777.888-99');
```

---

## ✏️ 3. ATUALIZAR DADOS (UPDATE)

### Atualizar telefone de um cliente
```sql
UPDATE CLIENTE 
SET TELEFONE = '(11) 99999-8888' 
WHERE ID = 1;
```

### Atualizar email e telefone
```sql
UPDATE CLIENTE 
SET EMAIL = 'joao.novo@email.com', 
    TELEFONE = '(11) 98888-7777' 
WHERE CPF = '123.456.789-01';
```

### Atualizar apenas o nome
```sql
UPDATE CLIENTE 
SET NOME = 'João Pedro Silva' 
WHERE ID = 1;
```

### Atualizar múltiplos registros (cuidado!)
```sql
UPDATE CLIENTE 
SET EMAIL = LOWER(EMAIL) 
WHERE EMAIL IS NOT NULL;
```

---

## 🗑️ 4. DELETAR DADOS (DELETE)

### Deletar cliente por ID
```sql
DELETE FROM CLIENTE WHERE ID = 3;
```

### Deletar cliente por CPF
```sql
DELETE FROM CLIENTE WHERE CPF = '666.777.888-99';
```

### Deletar clientes sem email
```sql
DELETE FROM CLIENTE WHERE EMAIL IS NULL;
```

### ⚠️ CUIDADO: Deletar TODOS os registros
```sql
-- USE COM CUIDADO! Remove todos os clientes
DELETE FROM CLIENTE;
```

---

## 🔍 5. CONSULTAS AVANÇADAS

### Contar clientes por domínio de email
```sql
SELECT 
    SUBSTRING(EMAIL, LOCATE('@', EMAIL) + 1) AS dominio,
    COUNT(*) AS total
FROM CLIENTE
WHERE EMAIL IS NOT NULL
GROUP BY SUBSTRING(EMAIL, LOCATE('@', EMAIL) + 1)
ORDER BY total DESC;
```

### Clientes com telefone mas sem email
```sql
SELECT * FROM CLIENTE 
WHERE TELEFONE IS NOT NULL 
AND EMAIL IS NULL;
```

### Buscar duplicatas de CPF (se houver)
```sql
SELECT CPF, COUNT(*) AS quantidade
FROM CLIENTE
GROUP BY CPF
HAVING COUNT(*) > 1;
```

### Últimos 5 clientes cadastrados
```sql
SELECT * FROM CLIENTE 
ORDER BY ID DESC 
LIMIT 5;
```

### Clientes cujo nome tem mais de 15 caracteres
```sql
SELECT NOME, LENGTH(NOME) AS tamanho
FROM CLIENTE
WHERE LENGTH(NOME) > 15
ORDER BY tamanho DESC;
```

---

## 🏗️ 6. ESTRUTURA DA TABELA

### Ver estrutura da tabela
```sql
SHOW COLUMNS FROM CLIENTE;
```

### Ver script de criação da tabela
```sql
SCRIPT TABLE CLIENTE;
```

### Ver informações detalhadas
```sql
SELECT * FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_NAME = 'CLIENTE';
```

---

## 🔄 7. TRANSAÇÕES

### Exemplo de transação
```sql
-- Iniciar transação
BEGIN;

-- Inserir cliente
INSERT INTO CLIENTE (NOME, EMAIL, TELEFONE, CPF) 
VALUES ('Teste Transacao', 'teste@email.com', '(11) 99999-9999', '999.999.999-99');

-- Verificar
SELECT * FROM CLIENTE WHERE CPF = '999.999.999-99';

-- Desfazer (Rollback)
ROLLBACK;

-- Ou confirmar (Commit)
-- COMMIT;
```

---

## 🧹 8. LIMPAR E RECRIAR

### Limpar todos os dados (mantém estrutura)
```sql
TRUNCATE TABLE CLIENTE;
```

### Deletar e recriar a tabela
```sql
DROP TABLE IF EXISTS CLIENTE;

CREATE TABLE CLIENTE (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NOME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100),
    TELEFONE VARCHAR(20),
    CPF VARCHAR(14) UNIQUE NOT NULL
);
```

### Popular tabela com dados de teste
```sql
INSERT INTO CLIENTE (NOME, EMAIL, TELEFONE, CPF) VALUES
('João Silva', 'joao@email.com', '(11) 98765-4321', '123.456.789-01'),
('Maria Santos', 'maria@email.com', '(21) 91234-5678', '987.654.321-00'),
('Pedro Oliveira', 'pedro@email.com', '(31) 99999-8888', '456.789.123-45'),
('Ana Costa', 'ana@email.com', '(41) 98888-7777', '111.222.333-44'),
('Carlos Souza', 'carlos@email.com', '(51) 97777-6666', '222.333.444-55');
```

---

## 🎯 9. VALIDAÇÕES E TESTES

### Testar constraint UNIQUE (deve dar erro)
```sql
-- Este comando deve FALHAR porque o CPF já existe
INSERT INTO CLIENTE (NOME, EMAIL, TELEFONE, CPF) 
VALUES ('Outro Nome', 'outro@email.com', '(11) 99999-9999', '123.456.789-01');
```

### Testar NOT NULL (deve dar erro)
```sql
-- Este comando deve FALHAR porque NOME não pode ser NULL
INSERT INTO CLIENTE (NOME, EMAIL, TELEFONE, CPF) 
VALUES (NULL, 'teste@email.com', '(11) 99999-9999', '000.000.000-00');
```

### Testar NOT NULL para CPF (deve dar erro)
```sql
-- Este comando deve FALHAR porque CPF não pode ser NULL
INSERT INTO CLIENTE (NOME, EMAIL, TELEFONE, CPF) 
VALUES ('Teste', 'teste@email.com', '(11) 99999-9999', NULL);
```

---

## 📈 10. ESTATÍSTICAS

### Estatísticas gerais
```sql
SELECT 
    COUNT(*) AS total_clientes,
    COUNT(EMAIL) AS com_email,
    COUNT(TELEFONE) AS com_telefone,
    COUNT(*) - COUNT(EMAIL) AS sem_email,
    COUNT(*) - COUNT(TELEFONE) AS sem_telefone
FROM CLIENTE;
```

### Listar clientes completos (todos os campos preenchidos)
```sql
SELECT * FROM CLIENTE 
WHERE EMAIL IS NOT NULL 
AND TELEFONE IS NOT NULL;
```

### Clientes com dados incompletos
```sql
SELECT 
    ID, 
    NOME,
    CASE WHEN EMAIL IS NULL THEN 'SEM EMAIL' ELSE EMAIL END AS email,
    CASE WHEN TELEFONE IS NULL THEN 'SEM TELEFONE' ELSE TELEFONE END AS telefone
FROM CLIENTE
WHERE EMAIL IS NULL OR TELEFONE IS NULL;
```

---

## 🔐 11. BACKUP E RESTORE

### Exportar dados para SQL
```sql
SCRIPT TO 'backup_clientes.sql' TABLE CLIENTE;
```

### Exportar para CSV
```sql
CALL CSVWRITE('clientes.csv', 'SELECT * FROM CLIENTE');
```

### Importar de CSV
```sql
CREATE TABLE CLIENTE_TEMP AS 
SELECT * FROM CSVREAD('clientes.csv');
```

---

## 🎲 12. DADOS DE TESTE ALEATÓRIOS

### Inserir 10 clientes de teste
```sql
INSERT INTO CLIENTE (NOME, EMAIL, TELEFONE, CPF) VALUES
('Alberto Ferreira', 'alberto.f@email.com', '(11) 91234-5678', '100.200.300-01'),
('Beatriz Almeida', 'beatriz.a@email.com', '(21) 92345-6789', '200.300.400-02'),
('Claudio Rocha', 'claudio.r@email.com', '(31) 93456-7890', '300.400.500-03'),
('Daniela Martins', 'daniela.m@email.com', '(41) 94567-8901', '400.500.600-04'),
('Eduardo Santos', 'eduardo.s@email.com', '(51) 95678-9012', '500.600.700-05'),
('Fabiana Lima', 'fabiana.l@email.com', '(61) 96789-0123', '600.700.800-06'),
('Gabriel Costa', 'gabriel.c@email.com', '(71) 97890-1234', '700.800.900-07'),
('Helena Ribeiro', 'helena.r@email.com', '(81) 98901-2345', '800.900.100-08'),
('Igor Pereira', 'igor.p@email.com', '(91) 99012-3456', '900.100.200-09'),
('Julia Carvalho', 'julia.c@email.com', '(11) 90123-4567', '101.202.303-10');
```

---

## 💡 DICAS

1. **Execute um comando por vez** para entender o resultado
2. **Use SELECT antes de UPDATE/DELETE** para verificar quais registros serão afetados
3. **Sempre use WHERE** em UPDATE e DELETE (exceto se quiser afetar todos os registros)
4. **Faça backup** antes de operações destrutivas
5. **Use transações** para operações críticas (BEGIN, COMMIT, ROLLBACK)
6. **Teste constraints** para garantir a integridade dos dados

---

## ⚡ ATALHOS DO CONSOLE H2

- **Ctrl + Enter**: Executar comando SQL
- **Ctrl + Space**: Auto-complete
- **F5**: Atualizar lista de tabelas
- **Histórico**: Use as setas ↑ ↓ para navegar pelos comandos anteriores

---

## 📚 REFERÊNCIAS

- [Documentação H2 Database](http://www.h2database.com/html/main.html)
- [SQL Tutorial](http://www.h2database.com/html/tutorial.html)
- [Funções SQL do H2](http://www.h2database.com/html/functions.html)

---

**Divirta-se testando! 🚀**

