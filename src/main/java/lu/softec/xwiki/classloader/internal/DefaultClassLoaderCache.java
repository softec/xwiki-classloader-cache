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

package lu.softec.xwiki.classloader.internal;

import java.net.URL;
import java.net.URLStreamHandlerFactory;

import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import lu.softec.net.CachedURLClassLoaderFactory;
import lu.softec.net.ComparableURLClassLoader;
import lu.softec.xwiki.classloader.ClassLoaderCache;

/**
 * Default implementation based on a CachedURLClassLoaderFactory
 *
 * @see CachedURLClassLoaderFactory
 */
@Component
@Singleton
public class DefaultClassLoaderCache implements ClassLoaderCache, Initializable {
    private CachedURLClassLoaderFactory cache;

    /**
     * {@inheritDoc}
     * @see Initializable#initialize()
     */
    public void initialize() throws InitializationException {
        cache = new CachedURLClassLoaderFactory();
    }

    /**
     * {@inheritDoc}
     * @see ClassLoaderCache#getURLClassLoader(java.net.URL[])
     */
    public ClassLoader getURLClassLoader(URL[] urls) {
        return cache.getURLClassLoader(urls);
    }

    /**
     * {@inheritDoc}
     * @see ClassLoaderCache#getURLClassLoader(java.net.URL[], java.lang.ClassLoader)
     */
    public ClassLoader getURLClassLoader(URL[] urls, ClassLoader parent) {
        return cache.getURLClassLoader(urls, parent);
    }

    /**
     * {@inheritDoc}
     * @see ClassLoaderCache#getURLClassLoader(java.net.URL[], java.lang.ClassLoader, java.net.URLStreamHandlerFactory)
     */
    public ClassLoader getURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        return cache.getURLClassLoader(urls, parent, factory);
    }

    /**
     * {@inheritDoc}
     * @see ClassLoaderCache#getURLClassLoader(java.net.URL[], boolean)
     */
    public ClassLoader getURLClassLoader(URL[] urls, boolean setVolatile) {
        ComparableURLClassLoader loader = cache.getURLClassLoader(urls);
        if( loader != null )
            loader.setVolatile(setVolatile);
        return loader;
    }

    /**
     * {@inheritDoc}
     * @see ClassLoaderCache#getURLClassLoader(java.net.URL[], java.lang.ClassLoader, boolean)
     */
    public ClassLoader getURLClassLoader(URL[] urls, ClassLoader parent, boolean setVolatile) {
        ComparableURLClassLoader loader =  cache.getURLClassLoader(urls, parent);
        if( loader != null )
            loader.setVolatile(setVolatile);
        return loader;
    }

    /**
     * {@inheritDoc}
     * @see ClassLoaderCache#getURLClassLoader(java.net.URL[], java.lang.ClassLoader, java.net.URLStreamHandlerFactory, boolean)
     */
    public ClassLoader getURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory, boolean setVolatile) {
        ComparableURLClassLoader loader = cache.getURLClassLoader(urls, parent, factory);
        if( loader != null )
            loader.setVolatile(setVolatile);
        return loader;
    }

    /**
     * {@inheritDoc}
     * @see ClassLoaderCache#refreshCache()
     */
    public boolean refreshCache() {
        return cache.refreshCache();
    }

    /**
     * {@inheritDoc}
     * @see ClassLoaderCache#getStats(java.lang.String)
     */
    public int getStats(String name) {
        if( name == null )
            return 0;

        DefaultClassLoaderStatNames stat = null;

        try {
            stat = DefaultClassLoaderStatNames.valueOf(name.trim().toUpperCase());
        } catch(Exception e) {}
        return getStats(stat);
    }

    /**
     * Fetch values of statistic counters
     * @param name name of the statictic counter to fetch
     * @return value of a the statistic counter
     * @see ClassLoaderCache#getStats(java.lang.String)
     */
    private int getStats(DefaultClassLoaderStatNames name) {
        switch(name) {
            case NEW:
                return cache.getStatNewLoader();
            case REUSED:
                return cache.getStatReusedLoader();
            case DROPPED:
                return cache.getStatDroppedLoader();
            default:
                return 0;
        }
    }

    /**
     * {@inheritDoc}
     * @see ClassLoaderCache#getStatNames()
     */
    public String[] getStatNames() {
        return DefaultClassLoaderStatNames.names();
    }
}
