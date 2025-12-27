# NEXT STEPS

## 1) Udělej „release“ moment pro todo demo (10 min)

připravit 2min WOW demo script pro firmy co presne rikat a ukazovat

Tagni verzi v GitHubu: v1.0-wow-todo

Do README přidej 3 řádky “Demo script” (co kliknout + URL)

Ověř, že funguje čistý start:

docker compose down --volumes
docker compose up --build

A jeste mi prosim zopakuj. Co startuji a kdy v PowerShelu. Maly kratky prehled.


## 2) projít docker-compose.yml řádek po řádku

Je tam i text Run service u backendu a Run service u frontendu. A potom Run all services. Toto se ma rucne naklikavat? Nebo vse startuji pouze z PowerShelu

## 3) Připrav 5min pitch + 10min deep-dive

- 5 min: “README → app → docker up”
- 10 min: “co dělá AI, co hlídáš ty (tests, PR, review)”
- highlight: “AI sice generuje, ale ty řídíš kvalitu a risk”

## 4) WOW demo #2: Web shop (React + Node.js) – super rychlé (největší efekt) (v novem vlakne)

Cíl: appka vznikne jen z README a běží do 1–2 minut.

### Stack (doporučení):
- Frontend: React + Vite
- Backend: Node.js + Express
- DB: SQLite (prisma) nebo jen in-memory (pro demo stačí)
- Docker Compose: 2 služby + případně db volume

### Funkce (minimum):
- produktový list
- detail produktu
- košík (local state)
- „checkout“ endpoint (mock)

Výhoda: build blesk, žádný Maven/Vaadin.

## 5) Udělej si “Claude Code WOW prompt” šablonu (opakovatelný proces) (v novem vlakne)

Vytvoř soubor WOW_PROMPT.md (do každého projektu):

- “Implement exactly per README”
- “All commands must work on Windows + Docker Desktop”
- “No PATCH unless client supports it”
- “Use PUT/POST/GET/DELETE only (demo-safe)”
- “Add smoke test endpoints”

Pak jen copy/paste do Claude Code.


## 6) přejít na Claude Code VS Code extension (profesionální daily usage) (v novem vlakne)

Nastavení, co chceš umět “profesionálně”:

- práce přes PR branch + commit messages
- refactor “one module at a time”
- lint/format/test jako povinný krok
- jak zadávat “do not touch X, only Y” (scope control)

## 7) Napojit na Claude Code VS Code velky lokalni java projekt, nechat ho zanalyzovat, najit rozdily ve verzim a nechat neco naimplementovat (v novem vlakne)