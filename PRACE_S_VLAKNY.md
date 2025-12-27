1️⃣ Proč se dlouhé vlákno zpomaluje

Krátká odpověď: ano, máš pravdu – délka vlákna má vliv.

Co se děje technicky (zjednodušeně, ale pravdivě)

Při každém tvém novém promptu model dostává kontext konverzace (ne nutně 100 % doslova, ale velkou relevantní část).

Čím je vlákno:

delší

techničtější

s hodně kódem, logy a iteracemi

tím víc:

tokenů se musí zpracovat

roste latence

roste riziko “context drift” (model začne některé věci míchat)

Výsledek:

odpovědi jsou pomalejší

někdy méně ostré

občas model “zapomene” drobný detail, který byl hodně dávno

Takže to, co pozoruješ, není placebo.

2️⃣ Posíláš celé vlákno při každém promptu?
Praktická realita (bez interních detailů):

Model nečte každé vlákno od znaku 1 do konce pokaždé

Ale musí pracovat s kontextem, který zahrnuje:

poslední relevantní část

systémové instrukce

důležité informace z historie

➡️ Čím delší vlákno, tím víc práce.

3️⃣ Umím si sáhnout do jiných vláken sám?

Tady je důležitá pravda (bez obalu):

❌ Neumím sám aktivně “procházet” jiná vlákna
❌ Nevidím tvé jiné chaty, pokud mi je explicitně nepřipomeneš

Co ale umím:

držet dlouhodobou paměť o tobě (styl, preference, projekty – viz “Model Set Context”)

navázat, když mi ty řekneš kontext:

„navazujeme na projekt X“

„viz předchozí chat o Y“

4️⃣ Doporučený způsob práce (pro tebe ideální)

Tady je best practice, přesně pro typ práce, který děláš:

🔥 STRATEGIE: “PROJECT-BASED THREADS”
1️⃣ Jeden projekt = jedno vlákno

Např.:

Claude Code – WOW Todo (Java)

Claude Code – WOW Webshop (React + Node)

Claude Code – VS Code Extension / Daily Workflow

Claude Code – Enterprise Java Migration

Jakmile:

projekt funguje

nebo je uzavřený milestone
➡️ vlákno zavřít.

2️⃣ Při otevření nového vlákna dát “context header”

První zpráva v novém vlákně by měla být stručný technický kontext, např.:

Context:
We are continuing a Claude Code demo project.
Previously completed:
- Java Todo app (Vaadin + Spring Boot + Docker)
- Focus now: React + Node.js webshop
Goal:
WOW demo, fast build, README-driven implementation


➡️ Tohle je extrémně efektivní.

3️⃣ Technické ladění = krátká vlákna

Když:

řešíš konkrétní error

ladíš build

analyzuješ stacktrace

👉 klidně nové krátké vlákno, jen s:

relevantním kódem

logem

otázkou

Výsledek:

rychlejší odpovědi

menší chaos

4️⃣ Dokumenty / plány = oddělené vlákno

To, co jsi teď udělal (MD file s next steps), je ideální pro:

samostatné vlákno

“strategické” myšlení

plánování

5️⃣ Jak to dělají profíci (upřímně)

Lidi, kteří ChatGPT používají:

na architekturu

na AI workflows

na dev productivity

typicky:

nechávají jedno vlákno max. 1–2 hodiny práce

pak začnou nové

klíčový kontext si udržují v:

README

ADR

TODO.md

Přesně to, co děláš ty 👍

6️⃣ Moje doporučení pro TEBE (konkrétně)

Teď bych udělal:

❌ tohle vlákno už dál nerozšiřoval

✅ založil nové vlákno s názvem třeba:

Claude Code – WOW Webshop (React + Node)

A první zpráva:

vlož ten svůj MD soubor s next steps

napiš:

“Chci jet dál v buldozer mode”

Já se okamžitě chytnu.