# StoreLiteApi
Versão atualizada do trabalho de faculdade para uma criação de API de um aplicativo que conecta o comércio com o consumidor.

## Endpoint

* Produto
    * Cadastrar
    * Atualizar Produto
    * Excluir Produto
    * Mostrar Produtos
* Mercado
    * Cadastrar
    * Atualizar Mercado
    * Excluir Mercado
    * Detalhar Mercado

---

### Cadastrar Produto

`POST` /api/produtos

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
nome | String | sim | Informa qual o produto comercializado
fabricate | String | sim | Informa qual empresa fabricou o produto
peso | double | sim | Informa o peso do produto
volume | double | não | Informa o volume do produto
categoria_id | int | sim | Informa em qual o grupo do produto
preco | double | sim | Valor a ser pago pelo produto
frete| double | não | Valor a ser pago pela entrega
quantidade | int | sim | Informa quanto do produto será vendido
vencimento | LocalDateTime | sim | Informa quanto a data de vencimento do produto 

**Exemplo de Campo de Requisição**

```js
    {
        "nome": "Sorvete de Morango",
        "fabricante": "Kibon",
        "peso": 1.0,
        "preco": 26.00,
        "mercado": {
            "id": 1,
	        "nome": "Super Markete"
        }
    }
```

**Código de Resposta**

código | descrição
|---|---
200 | Ok
201 | Produto criado com sucesso
400 | Campos enviados são inválidos

---

### Mostrar Produtos

`GET` /api/produtos/{id}

```js
    {
        "id": 1
        "nome": "Sorvete de Morango",
        "fabricante": "Kibon",
        "peso": 1.0,
        "preco": 26.00,
        "mercado": {
            "id": 1
        }
    }
```

---

### Atualizar Produtos

`PUT` /api/produtos/{id}

```js
    {
        "nome": "Sorvete de Chocolate",
        "fabricante": "Kibon",
        "peso": 1.0,
        "preco": 26.00,
        "mercado": {
            "id": 1
        }
    }
```

---

### Deletar Produtos

`DELETE` /api/produtos/{id}

---

### Cadastrar Mercado

`POST` /api/mercados

**Campo de Requisição**

campo | tipo | obrigatório | descrição
|---|---|:---:|---|
id | long | sim | Identificador único do mercado
nome| String | sim | Informa o nome do estabelecimento
endereco | int | sim | Informa o endereço do estabelecimento 
telefone | String | sim | Informa o telefone do estabelecimento
expediente_inicio | LocalDateTime | não | Informa o horário em que o estabelimento começa a funcionar
expediente_termino | LocalDateTime | não | Informa o horário em que o estabelecimento encerra as atividades
cnpj | String | sim | Informa o CNPJ da empresa
preco_minimo | double | não | Informa o preço mínimo para a realização de um produto

---

**Exemplo de Campo de Requisição**

```js
    {
        "nome": "Mercadinho Flash",
        "telefone": "463899975",
        "expediente_inicio": "2023-10-06T14:36:29.056Z",
        "expediente_termino": "2023-10-06T14:36:29.056Z",
        "cnpj": "77.575.656/0001-67",
        "preco_minimo": 20.0
    }
```

código | descrição
|---|---
200 | Ok
201 | Estabelecimento criado com sucesso
400 | Campos enviados são inválidos

---

### Detalhar Mercado

`GET` /api/mercados/{id}

```js
    {
        "id": 0,
        "nome": "Mercadinho Flash",
        "telefone": "463899975",
        "expediente_inicio": "2023-10-06T14:36:29.056Z",
        "expediente_termino": "2023-10-06T14:36:29.056Z",
        "cnpj": "77.575.656/0001-67",
        "preco_minimo": 20.0
    }
```

código | descrição
|---|---
200 | Ok
201 | Estabelecimento criado com sucesso
400 | Campos enviados são inválidos

### Atualizar Mercados

`PUT` /api/mercados/{id}

```js
    {
        "nome": "Mercadinho FlashNOW",
        "telefone": "463899975",
        "expediente_inicio": "2023-10-06T14:36:29.056Z",
        "expediente_termino": "2023-10-06T14:36:29.056Z",
        "cnpj": "77.575.656/0001-67",
        "preco_minimo": 10.0
    }
```

---

### Deletar Mercados

`DELETE` /api/mercados/{id}

---