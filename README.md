# 📄 API de Geração de PDF a partir de CSV

Esta API permite a conversão de arquivos CSV para PDF de forma eficiente e personalizável. 

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.2**
- **Spring Web**
- **OpenPDF (Manipulação de PDFs)**
- **Apache Commons CSV**

## 📌 Funcionalidades

- Upload de um arquivo CSV.
- Configuração do título e orientação do documento.
- Conversão do CSV para PDF.
- Retorno do PDF gerado para download.

## 📦 Instalação e Configuração

### Pré-requisitos

Certifique-se de ter instalado:

- **Java 17** ou superior
- **Maven**

### Clonando o Repositório

```bash
    git clone https://github.com/bRRAAz/CSVtoPDF
    cd CSVtoPDF
```

### Construção e Execução

Para rodar a API, utilize:

```bash
    mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080/api`

## 🔥 Uso da API

### Endpoint Disponível

#### **Gerar PDF**

**`POST /api`**

**Parâmetros:**

- `file` (**MultipartFile**) → O arquivo CSV a ser convertido.
- `title` (**String**) → O título do documento PDF.
- `rotate` (**String**) → Orientação do documento (padrão: retrato ou paisagem).

**Exemplo de Requisição:**

```bash
curl -X POST "http://localhost:8080/api" \
     -F "file=@caminho/do/arquivo.csv" \
     -F "title=Relatorio de Vendas" \
     -F "rotate=portrait"
```

**Resposta Esperada:**

- Um arquivo PDF gerado a partir dos dados do CSV.

--

Feito por [João Vitor Braz Tomedi](https://github.com/bRRAAz) 🚀

