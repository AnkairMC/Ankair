plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.ankairmc.ankair"
version = "1.13.2-R0.1-SNAPSHOT"

repositories {
    maven("https://maven.aliyun.com/nexus/content/groups/public/")
    maven("https://jitpack.io/")
    mavenCentral()
}

dependencies {
    /* network */
    implementation("io.netty:netty-all:4.1.114.Final")

    /* tools */
    implementation("com.google.guava:guava:33.3.1-jre")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("commons-io:commons-io:2.14.0")

    /* lombok */
//    compileOnly("org.projectlombok:lombok:1.18.34")

    /* test */
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.clean {
    delete(File(rootDir, "out"))
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "xyz.ankairmc.ankair.MinecraftServer"
        attributes["Multi-Release"] = true
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.withType(JavaCompile::class.java) {
    options.encoding = "UTF-8"
}
