/*
 * Copyright (C) 2010 SOFTEC sa.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 */

package lu.softec.xwiki.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

import org.xwiki.component.annotation.ComponentRole;

/**
 * A component for caching dynamically created class loaders
 */
@ComponentRole
public interface ClassLoaderCache {

    /**
     * Retrieve from the cache an existing compatible ClassLoader or create and store in the cache a new ClassLoader.
     * The default parent ClassLoader is used for delegation
     * No check are done to ensure freshness of the provided ClassLoader, you shoud use refreshCache() to ensure
     * freshness as required.
     *
     * @param urls the URLs from which to load classes and resources
     * @return a ClassLoader
     *
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkCreateClassLoader</code> method doesn't allow
     *             creation of a class loader or its <code>checkPermission</code> method
     *             does not allow the ReflectPermission("suppressAccessChecks") permission.
     *
     * @see URLClassLoader#URLClassLoader(java.net.URL[])
     */
    public ClassLoader getURLClassLoader(URL[] urls);

    /**
     * Retrieve from the cache an existing compatible ClassLoader or create and store in the cache a new ClassLoader.
     * No check are done to ensure freshness of the provided ClassLoader, you shoud use refreshCache() to ensure
     * freshness as required.
     *
     * @param urls the URLs from which to load classes and resources
     * @param parent the parent class loader for delegation
     * @return a ClassLoader
     *
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkCreateClassLoader</code> method doesn't allow
     *             creation of a class loader or its <code>checkPermission</code> method
     *             does not allow the ReflectPermission("suppressAccessChecks") permission.
     *
     * @see URLClassLoader#URLClassLoader(java.net.URL[], java.lang.ClassLoader)
     */
    public ClassLoader getURLClassLoader(URL[] urls, ClassLoader parent);

    /**
     * Retrieve from the cache an existing compatible ClassLoader or create and store in the cache a new ClassLoader.
     * No check are done to ensure freshness of the provided ClassLoader, you shoud use refreshCache() to ensure
     * freshness as required.
     *
     * @param urls the URLs from which to load classes and resources
     * @param parent the parent class loader for delegation
     * @param factory the URLStreamHandlerFactory to use when creating URLs
     * @return a ClassLoader
     *
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkCreateClassLoader</code> method doesn't allow
     *             creation of a class loader or its <code>checkPermission</code> method
     *             does not allow the ReflectPermission("suppressAccessChecks") permission.
     *
     * @see URLClassLoader#URLClassLoader(java.net.URL[], java.lang.ClassLoader, java.net.URLStreamHandlerFactory)
     */
    public ClassLoader getURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory);

    /**
     * Retrieve from the cache an existing compatible ClassLoader or create and store in the cache a new ClassLoader.
     * The default parent ClassLoader is used for delegation
     * No check are done to ensure freshness of the provided ClassLoader, you shoud use refreshCache() to ensure
     * freshness as required.
     *
     * @param urls the URLs from which to load classes and resources
     * @param setVolatile When true, changes to URLs are checked. If the ClassLoader is outdated, a new fresh
     * ClassLoader is created and stored in replacement in the cache. This feature may have a severe performance impact
     * since it require HEAD requests on each URLs.
     * @return a ClassLoader
     *
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkCreateClassLoader</code> method doesn't allow
     *             creation of a class loader or its <code>checkPermission</code> method
     *             does not allow the ReflectPermission("suppressAccessChecks") permission.
     *
     * @see URLClassLoader#URLClassLoader(java.net.URL[])
     */
    public ClassLoader getURLClassLoader(URL[] urls, boolean setVolatile);

    /**
     * Retrieve from the cache an existing compatible ClassLoader or create and store in the cache a new ClassLoader.
     * No check are done to ensure freshness of the provided ClassLoader, you shoud use refreshCache() to ensure
     * freshness as required.
     *
     * @param urls the URLs from which to load classes and resources
     * @param parent the parent class loader for delegation
     * @param setVolatile When true, changes to URLs are checked. If the ClassLoader is outdated, a new fresh
     * ClassLoader is created and stored in replacement in the cache. This feature may have a severe performance impact
     * since it require HEAD requests on each URLs.
     * @return a ClassLoader
     *
     * @exception  SecurityException  if a security manager exists and its
     *             <code>checkCreateClassLoader</code> method doesn't allow
     *             creation of a class loader or its <code>checkPermission</code> method
     *             does not allow the ReflectPermission("suppressAccessChecks") permission.
     *
     * @see URLClassLoader#URLClassLoader(java.net.URL[], java.lang.ClassLoader)
     */
    public ClassLoader getURLClassLoader(URL[] urls, ClassLoader parent, boolean setVolatile);

    /**
     * Retrieve from the cache an existing compatible ClassLoader or create and store in the cache a new ClassLoader.
     * 
     * @param urls the URLs from which to load classes and resources
     * @param parent the parent class loader for delegation
     * @param factory the URLStreamHandlerFactory to use when creating URLs
     * @param setVolatile When true, changes to URLs are checked. If the ClassLoader is outdated, a new fresh
     * ClassLoader is created and stored in replacement in the cache. This feature may have a severe performance impact 
     * since it require HEAD requests on each URLs.
     * @return a ClassLoader
     * 
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkCreateClassLoader</code> method doesn't allow 
     *             creation of a class loader or its <code>checkPermission</code> method 
     *             does not allow the ReflectPermission("suppressAccessChecks") permission.
     * 
     * @see URLClassLoader#URLClassLoader(java.net.URL[], java.lang.ClassLoader, java.net.URLStreamHandlerFactory) 
     */
    public ClassLoader getURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory, boolean setVolatile);

    /**
     * Check for changes to URLs of all ClassLoader currently in the cache and destroy outdated ClassLoader.
     * @return true if the cache has been changed
     */
    public boolean refreshCache();

    /**
     * Retrieve statistical counter regarding the activities of the cache. Counters are implementation dependent and the
     * list of available counters may be retrieved using getStatNames()
     * @param name name of a valid counters. Invalid counters silently return zero.
     * @return value of the requested counter.
     */
    public int getStats(String name);

    /**
     * @return the list of valid statistical counters
     */
    public String[] getStatNames();
}
