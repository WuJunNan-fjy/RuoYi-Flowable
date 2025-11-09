import type {App} from 'vue'
import {createI18n} from 'vue-i18n'
import {useLocaleStoreWithOut, elLocaleMap } from '@/store/modules/locale'
import type {I18n, I18nOptions} from 'vue-i18n'
import {setHtmlPageLang} from './helper'

export let i18n: ReturnType<typeof createI18n>

const createI18nOptions = async (): Promise<I18nOptions> => {
    let message;
    const localeStore = useLocaleStoreWithOut()
    const locale = localeStore.getCurrentLocale
    const localeMap = localeStore.getLocaleMap
    const defaultLang = 'zh-CN' // 默认语言 fallback

    try {
        // 动态导入语言包，失败则 fallback 到默认语言
        const defaultLocal = await import(`../../locales/${locale.lang}.ts`)
        message = defaultLocal.default ?? {};
    } catch (error) {
        console.warn(`语言包 ${locale.lang} 加载失败，使用默认语言 ${defaultLang}`)
        const defaultLocal = await import(`../../locales/${defaultLang}.ts`)
        message = defaultLocal.default ?? {}
    }

    setHtmlPageLang(locale.lang)

    // 补充 elLocale，避免丢失
    localeStore.setCurrentLocale({
        lang: locale.lang,
        elLocale: elLocaleMap[locale.lang] || elLocaleMap[defaultLang]
    })

    return {
        legacy: false,
        locale: locale.lang,
        fallbackLocale: defaultLang, // 找不到翻译时 fallback 到默认语言
        messages: {
            [locale.lang]: message
        },
        availableLocales: localeMap.map((v) => v.lang),
        sync: true,
        silentTranslationWarn: false, // 开启警告，方便调试翻译缺失问题
        missingWarn: true,
        silentFallbackWarn: false
    }
}

export const setupI18n = async (app: App<Element>) => {
    const options = await createI18nOptions()
    i18n = createI18n(options);
    app.use(i18n)
}
