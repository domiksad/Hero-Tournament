# RPG

## Podział na główne moduły:

- **Game** – główna logika gry
- **FightManager** – zarządzanie walką
- **Main (Startpoint)** – punkt wejścia

---

# System postaci

## AbstractCharacter

Bazowa klasa dla:

- Player
- Enemy

### Atrybuty:
- name : string
- health / maxHealth : int
- level / experience : int
- armor : int
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

### Atrybuty:
- name : string
- price : int
- levelRequirement : int
- description : string

### Metody:
- getDescription()


## Weapon (implements Item)

### Atrybuty:
- baseDamage : int
- damage : int
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

## StatType
```
enum StatType {
    HEALTH,
    ARMOR,
    DAMAGE
}
```

## Cooldown

### Atrybuty:
- int maxCooldown
- int currentCooldown

### Metody:
- Cooldown(int maxCooldown)
- bool isReady()
- void trigger() - set currentCooldown to maxCooldown
- int getRemaining() - get currentCooldown
- int getMaxCooldown()

# File structure
```
main-folder/
│
├─ src/
│   ├─ domain/            # logika gry / model obiektowy (w tym encje)
│   │   ├─ character/
│   │   │   ├─ AbstractCharacter.java
│   │   │   ├─ Player.java + encja
│   │   │   ├─ Enemy.java + encja
│   │   │   └─ EnemyFactory.java
│   │   │
│   │   ├─ items/
│   │   │   ├─ Item.java (interface)
│   │   │   └─ Weapon.java + encja
│   │   │
│   │   ├─ ultimate/
│   │   │   ├─ AbstractUltimate.java
│   │   │   └─ Ultimate.java + encja
│   │   │
│   │   ├─ effects/
│   │   │   ├─ AbstractEffect.java
│   │   │   └─ Effect.java + encja
│   │   │
│   │   └─ mechanics/
│   │       ├─ Cooldown.java
│   │       └─ StatType.java (enum)
│   │
│   ├─ application/       # logika wyższego poziomu / use cases
│   │   ├─ Game.java
│   │   └─ FightManager.java
│   │
│   ├─ infrastructure/    # zapis / odczyt (baza danych, pliki)
│   │   ├─ repository/
│   │   │   ├─ PlayerRepository.java
│   │   │   ├─ EnemyRepository.java
│   │   │   └─ WeaponRepository.java
│   │   └─ db/            # np. SQLite / JPA / Hibernate config
│   │
│   └─ presentation/      # UI / konsola / interfejs
│       ├─ ConsoleUI.java
│       └─ InputHandler.java
│
└─ resources/             # statyczne pliki, konfiguracja
```