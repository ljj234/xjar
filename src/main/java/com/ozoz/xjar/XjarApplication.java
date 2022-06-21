package com.ozoz.xjar;

import io.xjar.XEntryFilter;
import io.xjar.XKit;
import io.xjar.boot.XBoot;
import io.xjar.key.XKey;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class XjarApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(XjarApplication.class, args);

        // Spring-Boot Jar包加密
        String sourceJar = "/Users/ljj/Desktop/kx-screen-service-v1.1.0.0621.jar";
        String targetJar = "/Users/ljj/Desktop/demo-xjar.jar";
        XKey xKey = XKit.key("password");

//		XEntryFilter<JarArchiveEntry>
        XBoot.encrypt(
                sourceJar,
                targetJar ,
                xKey,
                new XEntryFilter<JarArchiveEntry>() {
                    @Override
                    public boolean filtrate(JarArchiveEntry jarArchiveEntry) {
                        String name = jarArchiveEntry.getName();
                        return name.startsWith("BOOT-INF/classes/") || name.startsWith("BOOT-INF/lib/io-xjar-");
                    }
                });
        System.out.println("Successfully generated encrypted jar");


    }

}
