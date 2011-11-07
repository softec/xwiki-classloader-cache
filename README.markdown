XWiki Class Loader Cache
========================

#### A class loader cache for XWiki ####

This project aims to facilitate Java classes reloading at run-time in XWiki.
It integrate our [dynamic-url-classloader project](http://github.com/softec/dynamic-url-classloader) 
in XWiki, providing a default classloader cache based on a CachedURLClassLoaderFactory.

### Targeted platforms ###

This implementation is dependant on the Sun JVM. This has been done to limitate,
as much as possible, the coding required by this implementation and benefit of
the existing URL, JAR and class handling provided in the Sun JVM implementation.
If you do not use the Sun JVM, you are out of luck, this project will not
helps you. Moreover, if you are using a security manager, this code require the
ReflectPermission("suppressAccessChecks") permission to access a private static
function of the Sun JVM.

This code has been heavily test under the Sun JVM 1.6

Contributing to this project
----------------------------

Fork our repository on GitHub and submit your pull request.

Documentation
-------------

There is no documentation for this project but the code is documented using JavaDoc.

License
-------

This code is copyrighted by [SOFTEC sa](http://softec.lu) and is licenced under
the terms of the [GNU Lesser General Public License](http://www.gnu.org/licenses/lgpl-2.1.html)
as published by the Free Software Foundation; either version 2.1 of the License, or
(at your option) any later version.
