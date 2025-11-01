# 🪄 Survival Debug Stick

**Version:** 1.0.0  
**Minecraft Version:** 1.18+  

---

## 📜 Description

The **Survival Debug Stick** plugin lets players craft and use the powerful Vanilla Debug Stick **directly in Survival**—no `/give` commands needed!  
It also protects certain blocks and states from unwanted interactions.

---

## ✨ Features

- Craft the **real Minecraft Debug Stick** via a configurable recipe.  
- Prevent interactions with **blacklisted blocks** and **blacklisted block tags**.  
- Block water interactions in selected worlds. 💧  
- Show **informative messages in chat or action bar** when interactions are blocked.  
- Fully configurable via `config.yml`:
  - Customize crafting ingredients and recipe shape. 🍴  
  - Set your own interaction-block messages. 💬  
  - World-specific water restrictions. 🌍

---

## ⚙️ Installation

1. Download the plugin JAR and place it in your server's `plugins` folder.  
2. Start the server to generate the `config.yml`.  
3. Adjust the `config.yml` as desired. ✏️  
4. Restart the server or reload the plugin (note: `/reload` is **not recommended**). 🔄  

---

## 🛠 Configuration

```yaml
Debug_stick_craft:
  enabled: true
  shape:
    line1: "N"
    line2: "S"
    line3: "S"
  ingredient:
    N: "NETHER_STAR"
    S: "STICK"

Blacklist_block:
  - bee_nest
  - cake
  - composter

Blacklist_tag_block:
  - candles
  - crops
