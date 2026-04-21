# Librareasy - Sistema de Gerenciamento de Biblioteca

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)

O **Librareasy** é um sistema robusto de automação de serviços bibliotecários desenvolvido para a disciplina de **Estrutura de Dados 1**. O foco principal do projeto é a implementação manual e a evolução de estruturas de dados lineares, partindo de uma abordagem estática para uma dinâmica.

---

## 🎯 Objetivo do Projeto
Resolver o problema de alta rotatividade e organização em bibliotecas através de um sistema que controla:
- Acervo de livros.
- Reservas e Fila de Espera.
- Empréstimos e Devoluções (com cálculo de multas).
- Histórico completo de operações.

---

## 🏗️ Estruturas de Dados (TADs Implementados)

### Etapa 1: Implementação Estática (Arrays)
| Entidade | Estrutura | Justificativa Técnica | Complexidade (Big O) |
| :--- | :--- | :--- | :--- |
| **Acervo** | **Lista Estática** | Armazenamento linear dos livros para consulta geral. | Busca: $O(n)$ |
| **Reservas** | **Lista Estática** | Controle de obras solicitadas vinculadas a usuários. | Inserção: $O(1)$ |
| **Fila de Espera**| **Fila Estática** | Segue o princípio **FIFO** (First-In, First-Out) para prioridade de reserva. | Enqueue: $O(1)$ |
| **Histórico** | **Pilha Estática** | Segue o princípio **LIFO** (Last-In, First-Out) para auditoria de ações recentes. | Push: $O(1)$ |

> **Nota de Evolução:** Na próxima etapa, estas estruturas serão migradas para **alocação dinâmica de memória** (Nós encadeados).

---

## 🚀 Como Executar

1. **Clonar o Repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/Librareasy.git](https://github.com/seu-usuario/Librareasy.git)
