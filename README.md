# 杀戮尖塔 ⑨ Mod

本仓库是一个 [**杀戮尖塔**](https://store.steampowered.com/app/646570/Slay_the_Spire/) 模组，添加了一名新的可选角色 [**琪露诺**](https://zh.moegirl.org/琪露诺)。

琪露诺是生活在雾之湖的一只妖精，拥有操纵冷气程度的能力，自称幻想乡最强。**寒冷** 能增加她的攻击与防御，但作为一只单纯的妖精，某些行动需要消耗她一定数额的 **干劲** 才能执行。

## 依赖模组
* [ModTheSpire](https://github.com/kiooeht/ModTheSpire) ：杀戮尖塔 Mod 加载器
* [BaseMod](https://github.com/daviscook477/BaseMod) ：一个 API Mod，同时也提供了游戏内调试用命令行
* [StSLib](https://github.com/kiooeht/StSLib) ：一个供其他 Mod 使用的关键词和机制的集合
* 
## 新机制
- **寒冷**：因为琪露诺是冰之妖精，所以她在低温下行动更加自如。
  - 每层 **寒冷** 会使角色的攻击伤害提高 1 点，或使角色获得的格挡提高 1 点。
  - 在玩家的回合开始时，如果 **寒冷** 层数大于 0，则会失去一半的 **寒冷** 层数（失去值向上取整）。

- **干劲**：因为琪露诺是妖精，所以她只有在充满干劲时才会执行某些行动。
  - 在打出带有关键字 **干劲 X** 的牌时，如果角色拥有的 **干劲** 至少为 X，便会消耗对应的 **干劲**，并获得特定的额外效果。
  - 特别地，如果牌面描述中 X 就是字符 "X"，则会消耗目前拥有的所有 **干劲**。
  - 某些牌也可以回复 **干劲**。角色拥有的 **干劲** 会以正面状态的形式存储在状态栏中。

## 致谢
  - Thanks to the [Marisa Mod](https://github.com/lf201014/STS_ThMod_MRS), which teaches me how to develop a mod of STS.
