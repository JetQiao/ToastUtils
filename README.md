[![](https://jitpack.io/v/JetQiao/ToastUtils.svg)](https://jitpack.io/#JetQiao/ToastUtils)
# 简易Toast

# Demo预览
<video src="images/demo_show.mp4" controls="controls"></video>

# 使用方法
```groovy
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}


dependencies {
    implementation 'com.github.JetQiao:ToastUtils:Tag'
}
```

```Kotlin
     ToastView(context).setMessage("show toast").show()
```