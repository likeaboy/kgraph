# kgraph
基于gephi图计算引擎的封装，输出GEXF图描述文件，帮助用户快速构建定制图谱

使用KGraph-framework绘制的gexf图描述文件在gephi中渲染的效果：
![image1](http://github.com/likeaboy/kgraph/raw/master/img/gephi_show.png)
![image2](http://github.com/likeaboy/kgraph/raw/master/img/490点分析图.png)

使用KGraph-framework绘制gexf图文件在echart中渲染的效果：
![image](http://github.com/likeaboy/kgraph/raw/master/img/echart_show.png)

## 例子
可以参见项目源码中的Demo类：

GetNodeDemo为根据节点数据生成单个节点，添加到Graph内存模型中，并输出到图描述文件gexf文件里。

GetRelatedNodeDemo以GetNodeDemo生成的gexf文件为基础，读入相关节点刷新Graph内存模型，并
生成新的gexf文件。
