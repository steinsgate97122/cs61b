/*
package: a namespace that organizes classes and interfaces
1. 访问same package内的class, 直接使用类名即可  eg: Dog d = new Dog();
2. 访问外部package的class，需要使用entire canonical name  eg: ug.joshh.animal.Dog d = new ug.joshh.animal.Dog(...);
   为了简化代码，也可以先import package，然后直接使用simple name

创建package
1. 给package内的所有文件顶部都加上package name
2. 例如，对于package ug.joshh.animal; 文件夹的目录结构应该是 ug/joshh/animal
在IDEA中创建package，file->new package，然后填写package name即可

顶部没有加package的java class都认为在default package内
实际项目中不应该将class放在默认package中，其他地方都无法访问default package内的class

如果希望共享某个program，可以将所有文件都压缩成一个jar文件，此文件内包括了所有的.class文件，另外有一些附加信息
jar file与zip file差不多，完全可以从jar包中还原出.java文件，不保证代码安全

idea中可以创建jar包，入口在file->project structure，选择Libraries是添加外部jar包，打包选择Artifacts（人工制品）
选择+ jar [from modules with dependencies] OK后关闭弹窗，然后选择build->build artifacts即可，可以在对应目录找到jar包
这个jar包就可以在Libraries导入

类似Libraries Artifacts这些操作在大型项目中会使用Build Systems来实现，类似Maven Gradle
 */
public class Demo01 {
    public static void main(String[] args) {
    }
}
