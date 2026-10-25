# Plano de Estudo — NotifyHub

**Carga:** 3h por semana, ~35 min por dia, de segunda a sexta
**Duração:** 12 semanas (uma semana pode se estender, desde que não pule a sexta)

## Rotina semanal
- **Segunda a quinta:** codificar à mão.
- **Sexta:** revisar e escrever no README o que foi feito e **por quê** (até 10 linhas).

## Regras
- IA é **consultora, não autora**: perguntar conceitos e erros pode; pedir código pronto não.
- Primeiro a documentação oficial e a Alura. A IA vem depois.
- Semana pronta = **funciona** + **tem teste** + **consigo explicar**.

## Caderno de lacunas (no trabalho)
Anote em uma linha tudo que não conseguiu explicar no trabalho. Use essa lista para ajustar o foco das semanas.

- [ ] ...

---

## Fase 1 — Base e banco

### Semana 1 — Setup
- [ ] Criar projeto Spring Boot com Kotlin (Gradle Kotlin DSL)
- [ ] Docker Compose com PostgreSQL
- [ ] Configurar Flyway e criar a migration da tabela `notificacoes`
- [ ] Entidade `Notificacao` e repositório JPA
- [ ] Endpoints: criar notificação e listar notificações de um usuário
- [ ] Teste básico do endpoint
- [ ] Sexta: README explicando a estrutura e o papel do Flyway

### Semana 2 — Performance SQL
- [ ] Script para gerar 20 mil notificações para um usuário
- [ ] Implementar paginação com OFFSET
- [ ] Implementar paginação por cursor (keyset)
- [ ] Criar índices `(usuario_id, id DESC)` e `(usuario_id, lida)` via migration
- [ ] Comparar as duas paginações com `EXPLAIN ANALYZE` (início e fim da lista)
- [ ] Endpoint de contador de não lidas
- [ ] Sexta: README com os resultados e por que o cursor é melhor

### Semana 3 — Testes
- [ ] Testes unitários do service com MockK
- [ ] Testes de integração com Testcontainers (Postgres real)
- [ ] Testar a paginação por cursor (primeira página, página do meio, última)
- [ ] Sexta: README explicando a diferença entre teste unitário e de integração

### Semana 4 — Transações
- [ ] Endpoint "marcar como lida" (só altera se ainda não estava lida)
- [ ] Endpoint "marcar todas como lidas"
- [ ] Estudar `@Transactional`: propagação, isolamento e rollback
- [ ] Reproduzir a armadilha de proxy (método transacional chamado de dentro da mesma classe)
- [ ] Teste de concorrência: duas requisições marcando a mesma notificação ao mesmo tempo
- [ ] Sexta: README explicando a armadilha de proxy e como evitá-la

---

## Fase 2 — Mensageria

### Semana 5 — RabbitMQ básico
- [ ] Adicionar RabbitMQ ao Docker Compose (com painel de management)
- [ ] Declarar exchange (topic), fila e binding
- [ ] Configurar conversor JSON para data classes Kotlin
- [ ] Producer: publicar evento ao "aceitar viagem"
- [ ] Consumer: salvar a notificação no banco
- [ ] Teoria: curso da Alura "Microsserviços na prática: mensageria com RabbitMQ"
- [ ] Sexta: README explicando exchange, fila, binding e routing key

### Semana 6 — Resiliência
- [ ] Configurar retry com backoff exponencial
- [ ] Criar DLX e DLQ
- [ ] Simular falha e verificar a mensagem chegando na DLQ
- [ ] Consumer da DLQ registrando o header `x-death`
- [ ] Idempotência: ID único no evento + unique constraint no banco
- [ ] Teste: mesmo evento processado duas vezes gera uma única notificação
- [ ] Sexta: README explicando retry, DLQ e idempotência

### Semana 7 — Tempo real
- [ ] Configurar WebSocket com STOMP
- [ ] Consumer enviando a notificação para o canal do usuário
- [ ] Front React mínimo: conectar, assinar o canal e exibir notificações
- [ ] Front: carregar a primeira página via API e o restante com scroll
- [ ] Reconexão: ao reconectar, buscar o que chegou desde o último ID recebido
- [ ] Sexta: README comparando polling e WebSocket

---

## Fase 3 — Cache e segurança

### Semana 8 — Redis
- [ ] Adicionar Redis ao Docker Compose
- [ ] Contador de não lidas com cache-aside e TTL
- [ ] Incrementar ao receber notificação e decrementar ao marcar como lida
- [ ] Enviar o contador atualizado pelo WebSocket (sincronia web e app)
- [ ] Fallback: aplicação continua funcionando com o Redis desligado
- [ ] Sexta: README explicando cache-aside e o papel do TTL

### Semana 9 — Segurança
- [ ] Spring Security com JWT
- [ ] Remover `usuarioId` dos parâmetros: obter do token
- [ ] Autenticar a conexão WebSocket
- [ ] Usuário só acessa e altera as próprias notificações
- [ ] Testes de segurança (acesso negado ao recurso de outro usuário)
- [ ] Sexta: README explicando o fluxo de autenticação

---

## Fase 4 — Nível sênior

### Semana 10 — Observabilidade
- [ ] Spring Boot Actuator (health, metrics)
- [ ] Métricas: tamanho das filas, mensagens na DLQ, latência de entrega
- [ ] Logs estruturados com um ID de correlação no evento
- [ ] Sexta: README explicando o que monitorar e por quê

### Semana 11 — Escala
- [ ] Subir duas instâncias da aplicação
- [ ] Reproduzir o problema: usuário conectado na instância A não recebe evento gerado na B
- [ ] Resolver com o RabbitMQ como broker relay do STOMP
- [ ] Sexta: README explicando o problema e a solução

### Semana 12 — Fechamento
- [ ] Diagrama da arquitetura no README
- [ ] Seção de decisões técnicas: cada escolha com motivo e trade-off
- [ ] Seção "o que eu faria diferente em produção"
- [ ] Revisar o código inteiro e conseguir explicar cada parte
- [ ] Publicar no GitHub e adicionar ao LinkedIn/currículo
