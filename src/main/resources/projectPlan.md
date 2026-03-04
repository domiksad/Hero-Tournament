# RPG

---

# System postaci

## AbstractCharacter

Bazowa klasa dla:

- Player
- Enemy

### Atrybuty:
- string name
- Health health
- Level level
- Stats stats
- equippedWeapon : Weapon
- ultimate : Ultimate
- List<Effect> activeEffects

### Metody:
- get<Stat>WithModifiers()
- gettery / settery


## Player

### Atrybuty:

- inventory : List<Item>
- gold : int

---

# System przedmiotów

## Item (interface)

### Metody:
- getDescription()
- getFullDescription
- getPrice()
- getLevelRequirement()


## Weapon (implements Item)

### Atrybuty:
- name : string
- price : int
- levelRequirement : int
- description : string
- damage : int
- Cooldown cooldown
- effects : Effect[]

---

# System efektów

## Effect (interface)

### Metody:
- int getStatModifier(StatType type)
- void apply(Character source, Character target) - logika efektu

---

# System Ultimate

## AbstractUltimate

### Atrybuty:
- name : string
- Cooldown cooldown

### Metody:
- getDescription()
- use() – logika (zadawanie obrażeń itp.)

---

# Inne

___

## Stats

### Atrybuty:
- damage
- armor

### Metody:
- Constructor
- Getters

___

## Cooldown

### Atrybuty:
- int initialValue
- int current
- int max

### Metody:
- Constructors
- bool isReady()
- void trigger() - set currentCooldown to maxCooldown
- void reset() - set currentCooldown to initialValue
- int getRemainingCooldown() - get currentCooldown
- int getMaxCooldown()

___

## Health

### Atrybuty:
- int current
- int max

### Metody:
- Constructor
- Getters
- Setters (with ability to chain)
- Health heal(int amount)
- Health damage(int amount)
- boolean isDead()

___

## Level

### Atrybuty:
- int experience
- int level

### Metody:
- Constructors
- int getExperienceTreshhold()
- Level addExperience(int amount)
- boolean hasLeveledUp()

# File structure
```
main/
│
├─ java.domiksad.heroTournament/
│   ├─ application/       # logika wyższego poziomu / use cases
│   │   └─ Game.java
│   │
│   ├─ domain/            # logika gry / model obiektowy (w tym encje)
│   │   ├─ character/
│   │   │   ├─ AbstractCharacter.java
│   │   │   ├─ Player.java + encja
│   │   │   ├─ Enemy.java + encja
│   │   │   └─ EnemyFactory.java
│   │   │
│   │   ├─ config/
│   │   │   └─ GameBalance.java
│   │   │
│   │   ├─ effects/
│   │   │   ├─ AbstractEffect.java
│   │   │   └─ Effect.java + encja
│   │   │
│   │   ├─ items/
│   │   │   ├─ Item.java (interface)
│   │   │   ├─ Weapon.java + encja
│   │   │   └─ WeaponFactory.java
│   │   │
│   │   ├─ mechanics/
│   │   │   ├─ Cooldown.java
│   │   │   ├─ Health.java
│   │   │   ├─ Level.java
│   │   │   └─ StatType.java (enum)
│   │   │
│   │   └─ ultimate/
│   │       ├─ AbstractUltimate.java
│   │       └─ Ultimate.java + encja
│   │
│   ├─ infrastructure/    # zapis / odczyt (baza danych, pliki)
│   │   ├─ db/            # np. SQLite / JPA / Hibernate config
│   │   │
│   │   └─ repository/
│   │       ├─ PlayerRepository.java
│   │       ├─ EnemyRepository.java
│   │       └─ WeaponRepository.java
│   │
│   └─ Main.java          # Entry point
|
└─ resources/             # statyczne pliki, konfiguracja
    └─ projectPlan.md
```