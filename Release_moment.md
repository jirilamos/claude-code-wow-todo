# RELEASE MOMENT

## 1) “Release moment” pro todo demo
A) 2min WOW demo script (co říkat + co ukázat)

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

