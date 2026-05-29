TRUNCATE TABLE flower_types RESTART IDENTITY CASCADE;

INSERT INTO flower_types (name, care_instructions)
VALUES ('Alstroemeria',
        '• Do not cut the stems: gently pull them from the base if possible to encourage new growth.
• Place in a clean vase with fresh, cool water and change it every 2–3 days.
• Remove leaves below the waterline.
• Keep away from direct sunlight, heat, and drafts.
• Store in a cool place to extend vase life.
With proper care, Alstroemeria remains fresh and continues to open new blooms over time.'),

       ('Amaryllis',
        '• Cut the stems straight across and place in water immediately.
   • Use a clean vase with only a small amount of water, as stems are hollow and absorb quickly.
   • Change water every 1–2 days.
   • Keep away from heat and direct sunlight.
   • Support stems if they become top-heavy.
   With simple care, Amaryllis keeps its bold structure and open blooms for many days.'),

       ('Anemone', 'Standard care instructions for Anemone'),
       ('Astilbe', 'Standard care instructions for Astilbe'),
       ('Calla Lily',
        '• Trim the stems at a slight angle before placing them in water.
   • Use a small amount of water, as stems can become soft in too much moisture.
   • Refresh water every 1–2 days.
   • Handle gently, as stems bruise easily.
   • Keep in a cool place, away from direct sunlight.
   With gentle care, Calla Lilies maintain their clean shape and smooth texture.'),

       ('Carnation', 'Standard care instructions'),
       ('Chrysanthemum', 'Standard care instructions for Chrysanthemum'),
       ('Freesia', 'Standard care instructions for Freesia'),
       ('Hydrangea',
        '• Cut stems at a 45° angle and place immediately in water.
• Submerge stems briefly in water after cutting to prevent air blockage.
• Use a vase filled with plenty of fresh water and change it daily.
• Mist the blooms regularly, as they absorb moisture through petals.
• Keep in a cool, shaded area.
With attentive care, Hydrangea stays full and hydrated longer.'),

       ('Lilac', 'Standard care instructions for Lilac'),
       ('Lily',
        '• Trim stems at a 45° angle before placing in water.
• Use clean, cool water and refresh it every 2 days.
• Remove leaves below the waterline.
• Keep away from heat, drafts, and direct sunlight.
• Allow buds time to open naturally.
With proper care, Lisianthus gradually opens and maintains a light, soft appearance.'),
       ('Limonium', 'Standard care instructions for Limonium go here...'),
       ('Lisianthus', 'Standard care instructions for Lisianthus go here...'),
       ('Orchid',
        '• Trim stems slightly before placing in water.
• Use a clean vase with a small amount of fresh water.
• Change water every 2–3 days.
• Avoid temperature changes and direct sunlight.
• Keep away from ripening fruit (ethylene shortens lifespan).
With gentle care, cut Orchids remain fresh and stable for an extended period.'),
       ('Peony', '• Trim stems at a 45° angle and place in water immediately.
• Use a clean vase with fresh, cool water and change it every 1–2 days.
• If buds are closed, keep in a warm room to encourage opening.
• Remove leaves below the waterline.
• Keep away from direct sunlight once opened.
With proper care, Peonies open fully and maintain their soft, layered shape.'),
       ('Ranunculus', '• Trim stems at a 45° angle before placing in water.
• Use a small amount of fresh, cool water (stems are delicate).
• Change water daily or every 2 days.
• Keep in a cool environment away from heat.
• Support stems if they begin to bend.
With simple care, Ranunculus keeps its layered petals and soft structure longer.'),
       ('Rose', '• Trim the stems at a 45° angle before placing them in water to improve water absorption.
• Use a clean vase filled with fresh, cool water and change the water every 1-2 days.
• Remove any leaves that sit below the waterline to keep the water clear and fresh.
• Keep the bouquet away from direct sunlight, heaters, and drafts.
• Place the flowers in a cool, shaded spot to help the blooms stay soft and fresh longer.
With gentle care, the Rose will maintain its delicate color and graceful shape for days, bringing a calm, romantic mood to your space'),
       ('Stock', 'Standard care instructions for Stock'),
       ('Mixed flowers', '• Trim all stems at a 45° angle before placing them in water.
• Use a clean vase with fresh, cool water and change it every 1–2 days.
• Remove all foliage below the waterline.
• Keep away from direct sunlight, heat, and drafts.
• Re-trim stems every few days to maintain water uptake.
With balanced care, mixed flowers stay fresh while different blooms open at their own pace.
');
