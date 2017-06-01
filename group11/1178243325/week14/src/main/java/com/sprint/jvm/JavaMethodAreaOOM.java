public class JavaMethodAreaOOM {
	// 设置VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
	// jdk1.7往后, 需要借助CGLib实现PermGen space
	// 实现PermGen space，一种将"常量池"撑死
}
