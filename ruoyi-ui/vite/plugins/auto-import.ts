import AutoImport from 'unplugin-auto-import/vite';
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers';
export default function createAutoImport() {
    return AutoImport({
        dts: 'src/auto-imports.d.ts',
        imports: [
            'vue',           // ← 必须写
            'vue-router',
            'pinia',
            '@vueuse/core',
            { '@/hooks/web/useI18n': ['useI18n'] } // 自动引入自定义 useI18n
        ],
        resolvers: [ElementPlusResolver()],
    });
}
