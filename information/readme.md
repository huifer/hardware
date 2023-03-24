# 设备基础信息

本项目用于存储设备基础信息：
1. [HardwareTypeEntity.java](src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fhuifer%2Fhardware%2Finformation%2Fentity%2FHardwareTypeEntity.java)设备类型（这是人为的一种分类标准，类似液位计，流量计他是一个概化的描述）
2. [HardwareSignalEntity.java](src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fhuifer%2Fhardware%2Finformation%2Fentity%2FHardwareSignalEntity.java)设备信号（这是用户对一类设备类型所关注的设备信号，并不是真实的物理设备产生的信号）
3. [HardwareInfoEntity.java](src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fhuifer%2Fhardware%2Finformation%2Fentity%2FHardwareInfoEntity.java)设备详情（用于存储真实物理设备详细信息，）
4. [HardwareSignMappingEntity.java](src%2Fmain%2Fjava%2Fcom%2Fgithub%2Fhuifer%2Fhardware%2Finformation%2Fentity%2FHardwareSignMappingEntity.java)信号映射（用于存储硬件类型中的信号和物理设备信号之间的关系）