import AutoImport from 'unplugin-auto-import/vite';
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers';
export default function createAutoImport() {
    return AutoImport({
        dts: 'src/auto-imports.d.ts',
        imports: [
            'vue',           // ← 必须写
            'vue-router',    // 按需
            'pinia',         // 按需
            '@vueuse/core'   // 按需
        ],
        resolvers: [ElementPlusResolver()],
    });
}
