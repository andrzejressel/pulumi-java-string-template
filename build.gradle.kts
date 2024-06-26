import pl.andrzejressel.plugin.License

plugins {
  id("java")
  id("parent-plugin")
  id("child-plugin")
}

group = "pl.andrzejressel"

childPlugin {
  license = License.LGPL
  disableJavaFormatter = true
  childAndParentInTheSameProject = true
}

repositories { mavenCentral() }

java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

dependencies {
  implementation("com.pulumi:pulumi:0.12.0")
  testImplementation(platform("org.junit:junit-bom:5.10.2"))
  testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test { useJUnitPlatform() }

tasks.withType<JavaCompile>().configureEach { options.compilerArgs.add("--enable-preview") }

tasks.withType<Test>().configureEach { jvmArgs("--enable-preview") }

tasks.withType<JavaExec>().configureEach { jvmArgs("--enable-preview") }
