# RELEASE MOMENT

## “Release moment” pro todo demo
### 2min WOW demo script (co říkat + co ukázat)

0:00–0:20
„Tahle aplikace vznikla z jednoho README.md přes Claude Code. Ukážu, že ji umím spustit jedním příkazem.“

0:20–0:45
V terminálu:

docker compose down
docker compose up


„Tohle postaví i spustí celý stack.“

0:45–1:20
Otevři UI: http://localhost:8080

přidej todo „Buy milk“

klikni checkbox (done/undone)

smaž todo

1:20–1:45
Otevři health: http://localhost:8081/actuator/health
„Backend je Spring Boot REST + SQLite, health endpoint běží.“

1:45–2:00
„AI vyrobila baseline, ale já řídím kvalitu: fixoval jsem reálné build issues (theme, PATCH, DB schema), přidal demo-safe REST kontrakt a udělal reproducible Docker start.“

---

## Mini shrnutí (na zapamatování)

🔴 Nejpomalejší / nejcitlivější: docker compose up --build

🟠 Ještě tvrdší (čistý rebuild): docker compose build --no-cache

🟢 Běžné spuštění (rychlé): docker compose up

Pro WOW demo: stačí docker compose up
--build ukazuj jen když chceš demonstrovat „od README až po běžící appku“.

## 5min pitch + 10min deep-dive (stručný outline)
### 5 min pitch
- README = specifikace
- Claude Code = implementace + PR
- Docker Compose = reprodukovatelný run
- ukázka UI + health

### 10 min deep-dive
- co AI udělala (kód, docker, CRUD)
- co jsi řídil ty (bugfixy, kontrakty, spolehlivost, PR/merge)
- rizika a kontrola (testy, small diffs, review)